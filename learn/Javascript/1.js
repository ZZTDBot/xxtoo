let book = {
    name: "Javascript"
  }; 
  let descriptor = Object.getOwnPropertyDescriptor(book, 'name');
  console.log(JSON.stringify(descriptor, null, 2));
  /* property descriptor:
  {
    "value": "Javascript",
    "writable": true,
    "enumerable": true,
    "configurable": true
  }
  */