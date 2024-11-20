let paused = false;
let receivedLength = 0;
let chunks = [];
let abortController = null;
let downloadUrl = "";
let fileId = "";

self.onmessage = async (e) => {

  const { action, url, id } = e.data;

  if (action === "pause") {
    paused = true;
    if (abortController) abortController.abort(); 
    return;
  }

  if (action === "resume") {
    if (paused && downloadUrl && fileId) {
      paused = false;
      await downloadFile(downloadUrl, fileId);
    }
    return;
  }

  if (action === "start") {

    paused = false;
    receivedLength = 0;
    chunks = [];
    downloadUrl = url; 
    fileId = id; 
    await downloadFile(downloadUrl, fileId);
  }

};

async function downloadFile(url, id) {

  try {

    abortController = new AbortController();

    // start resume
    const headers = receivedLength > 0 ? { Range: `bytes=${receivedLength}-` } : {};
    
    const response = await fetch(url, { signal: abortController.signal, headers });


    if (!response.ok) {

      self.postMessage({ type: "error", error: response.statusText, id });

      return;

    }

    // specifies the total size (in bytes) of the file being downloaded (string)
    const contentLength = parseInt(response.headers.get("Content-Length")) + receivedLength;

    console.log(contentLength);
    
    // ReadableStream object that allows you to read the file data in chunks.
    // getReader() method provides a way to work with the underlying byte stream of the file.
    const reader = response.body.getReader();

    while (true) {

      if (paused) return;

      // download in chunks using read
      const { done, value } = await reader.read();

      if (done) break;

      chunks.push(value); 

      receivedLength += value.length;

      console.log(receivedLength);

      const progress = Math.round((receivedLength / contentLength) * 100);

      self.postMessage({ type: "progress", progress, id });

    }
    
    // join the all parts of file downloaded in chunks
    const blob = new Blob(chunks, { type: "audio/mp4" });

    self.postMessage({ type: "completed", blob, id }); 


  } catch (error) {

    if (!paused) {

      self.postMessage({ type: "error", error: error.message, id });

    }

  }

}
