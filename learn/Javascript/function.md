<h1 align="center">function | 函式</h1>

<div align="center">  <!-- -->

</div> <!-- HTML 分割标签，简称“div”，是一个特殊的元素，可以让你在网页上把类似的内容集合起来。 你可以把它作为关联类似内容的通用容器来使用。 -->

## 什么是函式？

函式是一段预先写好的程式码
那我们今天呼叫函式的时候，那段程式码才会被执行，
也就是说函式分成两个部分
function 并不是一种特定的编程语言，而是大多数编程语言都支持的一个关键字，用于定义函数（或方法）。 例如，JavaScript、Python、PHP、Java、C++等编程语言都使用 function 来定义函数。

1. 函式的定义 if 函式的呼叫

```js
//这里定义了一个函式 hello ，
/**
 *
 * @param {*} num1 参数1
 * @param {*} num2 参数2
 * @returns
 */
function hello(){
    //预先写好的程式码式
    document.write("你好")；
}
//这里式对函式的呼叫
hello();
```

```js
function hello(name) {
  document.write("你好" + name);
}
//这样写的意思就是把 熊猫 这个字串传到上方 （name）里面
hello("熊猫");
```

```js
function hello(name,age) {
  document.write("你好" + name "你今年" + age + "几岁");
}

// ("熊猫","20") 对应 上方 (name,age)
hello("熊猫","20");
```

2. 假设我们定义一个函式，它的名字叫 add 它可以传入两个咨询，传入两个数值
   它会把我们两个数做相加

```js
//所以我们先定义add函数 我们设置为 num1 和 num2  需要有两个参数的相加
function add(num1, num2) {
  document.write(num1 + num2); // 最后在函数体里面返回 num1 加 num2 的结果
}

add(8, 7);
```

3. 再介绍一个函式非常重要的概念回传 return
   这个 return num1 + num2 是什么意思呢？
   它会把我们回传的值覆盖掉原先的呼叫 add(8, 7);

```js
function add(num1, num2) {
    //document.write(num1 + num2);
    return num1 + num2;             // 等于 return =9; 在函数体里面返回 num1+num2 的结果

}
//add(3,6);
document.write( add{3,6});       // 也就等于 document.write(9)
```

那为什么用回传呢？ 因为我们的函数很多时候不是就让它显示出来结果
而是做出很多处理 比如运行处理 document.write( 9 \* 100 +60 )；
乘以 100 阿 加 60 等等等等.....

4. 如果是以下方法写的话会出现什么样的结果呢？

```js
function add(num1, num2) {
  document.write(num1 + num2);
  document.write("<br/>");
  return 10;
  document.write("你好"); // 这里的操作不会继续执行因为 return 就跳出一个函式
}
value = add(3, 2);
document.write(value);
```

结果是

```
5
10
```

【javascript】函式 function 介绍
https://www.youtube.com/watch?v=otFezWePsBc