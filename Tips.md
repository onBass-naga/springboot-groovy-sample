# Tips

## IntelliJ IDEA でホットリロードを有効にする

メニューをたどって、該当のチェックボックスをONにする。  
` preferencies -> Build Excution, Deployment -> Compiler -> Make project automatically `   
コンソールから `./gradlew bootRun` で起動する。  

[参考：Intellij IDEA + gradleでspring loadedが動かない問題](http://www.bunkei-programmer.net/entry/2015/06/16/012524)

## GradleのThymeleafホットリロード設定

下記設定を `build.gradle` に追加する

```
bootRun {
    addResources = true
}
```

## Groovy 関連

### BeanUtils#copyProperties を使うとidプロパティが消える

```
Customer customer = new Customer()
BeanUtils.copyProperties(customer, customer)
customer.id = id // <= ここで `customer.id` は無いと怒られてしまう
```

