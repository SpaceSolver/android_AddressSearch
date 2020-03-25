# android_AddessSearch
## 概要  
郵便番号から住所を検索する

## アプリの動作  
記号後、郵便番号を入力し画面下のボタンを押下すると  
WebAPIのレスポンスから住所が画面に表示されます。  
※出力欄に番地等を追加できることを確認するため、出力欄にもeditTextを使用しています。  

## 郵便番号検索APIについて  
郵便番号検索APIは、日本郵便が公開している郵便番号データを検索する機能をRESTで提供しています。  
ベースとなるURLは以下になります。  
https://zip-cloud.appspot.com/api/search  
※このURLにリクエストパラメータを加え、リクエストを行います。  

[郵便番号検索API](http://zipcloud.ibsnet.co.jp/doc/api)

## 備考  
API28以上の端末を対象としています。

※API25(Nexus6)向けにビルドすると、AndroidStadoのエラーにより  
ビルドがうまく通りませんでした。  
エラー原因：  
android - the - Error:Ambiguous method call. Both findViewById(int) in AppCompactActivity and Activity   

