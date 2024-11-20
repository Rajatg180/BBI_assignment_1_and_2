let workers = [];

const max = 1000000;

let startTime, endTime;

const statusEl = document.getElementById("status");
const resultEl = document.getElementById("result");
const executionTimeEl = document.getElementById("executionTime");
const workerInput = document.getElementById("workerInput");
const loader = document.getElementById("loader");

// getting the number of workers
const logicalCores = navigator.hardwareConcurrency;

console.log(logicalCores);

if (!logicalCores) {
  alert("Unable to detect the number of logical cores on your system.");
}

// Single Thread

document.getElementById("singleThreadBtn").addEventListener("click", () => {

  statusEl.textContent = "Running (Single-Threaded)";

  loader.style.display = "block";

  startTime = performance.now();

  const result = calculatePrimes(max);

  endTime = performance.now();

  loader.style.display = "none";

  displayResult(result, "Single-Threaded");
});

function calculatePrimes(max) {
  const primes = [];

  for (let i = 2; i <= max; i++) {
    if (isPrime(i)) {
      primes.push(i);
    }
  }

  return primes;
}

// single threading ( without blocking main thread  )

// document.getElementById("singleThreadBtn").addEventListener("click", () => {

//     statusEl.textContent = "Running (Single-Threaded)";

//     loader.style.display = "block";

//     startTime = performance.now();

//     let primes = [];

//     let i = 2;

//     function calculateChunk() {

//       const chunkSize = 100000;

//       console.log(`Chunks ${i}`);

//       for (let j = i; j < i + chunkSize && j <= max; j++) {

//         if (isPrime(j)) {

//           primes.push(j);

//         }

//       }

//       i += chunkSize;

//       if (i <= max) {

//         setTimeout(calculateChunk, 0);

//       } else {

//         endTime = performance.now();

//         loader.style.display = "none";

//         displayResult(primes, "Single-Threaded");

//       }

//     }

//     calculateChunk();

// });

function isPrime(num) {
  for (let i = 2; i <= Math.sqrt(num); i++) {
    if (num % i === 0) {
      return false;
    }
  }

  return num > 1;
}



// Multi Thread
document.getElementById("multiThreadBtn").addEventListener("click", () => {
  
  const count = parseInt(workerInput.value);

  if (!count || count <= 0) {
    alert("Please enter a valid number of workers.");
    return;
  }

  if (count > logicalCores) {
    alert(
      `Your system supports a maximum of ${logicalCores} threads. Please re-enter a valid number.`
    );
    return;
  }

  statusEl.textContent = "Running (Multi-Threaded)";

  loader.style.display = "block";

  startTime = performance.now();

  runMultiThreaded(max, count);
});

function runMultiThreaded(max, count) {

  const range = Math.ceil(max / count);

  let completed = 0;

  let primes = [];

  const workerTimes = [];

  cleanWorkers();

  for (let i = 0; i < count; i++) {

    const worker = new Worker("worker-thread.js");

    const workerStartTime = performance.now();

    worker.postMessage({ start: i * range + 1, end: (i + 1) * range });

    worker.onmessage = (e) => {

      const workerEndTime = performance.now();

      const workerTime = (workerEndTime - workerStartTime).toFixed(2);

      workerTimes.push(`Worker ${i + 1}: ${workerTime} ms`);

      primes = primes.concat(e.data);

      completed++;

      if (completed === count) {
        
        endTime = performance.now();

        loader.style.display = "none";

        displayResult(primes, "Multi-Threaded", workerTimes);

        cleanWorkers();
      }
    };

    worker.onerror = (e) => {
      console.error("Worker error:", e.message);
      cleanWorkers();
    };

    workers.push(worker);
  }
}

function cleanWorkers() {
  
  workers.forEach((worker) => worker.terminate());

  workers = [];
}

function displayResult(result, method, workerTimes = []) {

  const executionTime = (endTime - startTime).toFixed(2);

  statusEl.textContent = `Status: Completed (${method})`;

  resultEl.textContent = `Result: ${result.length} primes`;

  executionTimeEl.textContent = `Execution Time: ${executionTime} ms`;

  const workerTimesDisplay = workerTimes
    .map((time) => `<p>${time}</p>`)
    .join("");

  if (workerTimes.length) {
    executionTimeEl.insertAdjacentHTML(
      "beforeend",
      `<h3 >Execution Time for Each Worker</h3>`
    );

    executionTimeEl.insertAdjacentHTML(
      "beforeend",
      `<div>${workerTimesDisplay}</div>`
    );
  }
}
