onmessage = function (e) {
  const { start, end } = e.data;

  const primes = calculatePrimesInRange(start, end);

  postMessage(primes);
  
};

function calculatePrimesInRange(start, end) {

  const primes = [];

  for (let i = start; i <= end; i++) {

    if (isPrime(i)) {

      primes.push(i);

    }

  }

  return primes;
}

function isPrime(num) {
  for (let i = 2; i <= Math.sqrt(num); i++) {
    if (num % i === 0) {
      return false;
    }
  }
  return num > 1;
}
