/**
 * 1.Database Creation: 数据库创建
 * 开发人员通过指定名称和版本来创建 IndexedDB 数据库：
 * @IDBOpenDBRequest 是IndexedDB API 的接口，提供对打开或删除数据库的请求结果的访问
 * （使用 IDBFactory.open 和 IDBFactory.deleteDatabase），使用特定的事件处理程序属性。
 * 
 */ 
const request = indexedDB.open('myDatabase', 1);


/**
 * 2.Object Stores：对象储存
 * 在数据库中，开发人员创建对象存储，这类似于关系数据库中的表。对象存储定义数据记录的结构：
 * 
 */
request.onupgradeneeded = function(event) {
    const db = event.target.result;
    const objectStore = db.createObjectStore('customers', { keyPath: 'id' });
    objectStore.createIndex('name', 'name', { unique: false });
  };


/**
 * 3.Data Operations: 数据操作
 * 开发人员可以对对象存储中的数据记录执行 CRUD（创建、读取、更新、删除）操作。例如，添加一条记录：
 */
const transaction = db.transaction(['customers'], 'readwrite');
const objectStore = transaction.objectStore('customers');
const customer = { id: 1, name: 'John Doe', email: 'john@example.com' };
const request = objectStore.add(customer);

/**
 * 4.Queries: 查询
 * IndexedDB 允许开发人员在对象存储属性上创建索引，以实现高效的数据查询。例如，要按姓名检索客户：
 */
const transaction = db.transaction('customers');
const objectStore = transaction.objectStore('customers');
const index = objectStore.index('name');
const request = index.get('John Doe');

/**
 * 
 * 5.Transactions:交易
 * 事务在进行多个数据库更新时确保数据完整性和原子性。它们可用于将多个操作分组为单个事务：
 */
const transaction = db.transaction(['customers'], 'readwrite');
const objectStore = transaction.objectStore('customers');
objectStore.put({ id: 1, name: 'Updated Name', email: 'updated@example.com' });

/**
 * 使用索引数据库
 * 以下是如何创建简单 IndexedDB 数据库、添加数据和检索数据的基本示例：
 */
// 打开或创建数据库
const request = indexedDB.open('myDatabase', 1);

// 创建对象存储并添加数据
request.onupgradeneeded = function(event) {
  const db = event.target.result;
  const objectStore = db.createObjectStore('customers', { keyPath: 'id' });
  objectStore.add({ id: 1, name: 'John Doe', email: 'john@example.com' });
};

// 撷取资料、取回资料
request.onsuccess = function(event) {
  const db = event.target.result;
  const transaction = db.transaction('customers');
  const objectStore = transaction.objectStore('customers');
  const request = objectStore.get(1);

  request.onsuccess = function(event) {
    const customer = event.target.result;
    console.log('检索客户:', customer);
  };
};
