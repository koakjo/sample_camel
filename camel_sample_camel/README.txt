

## camelのサンプルプロジェクト。
Springを使用しない形。
fat jarにて動作（アプリケーションサーバなしで動けることを確認したかったため）

なお、動作させる際の注意点は以下。
（不便で申し訳ありません）

1. ポート番号の指定ができない
→設定ファイル等はなくコード内にベタ書きしている。
本来であればポート番号も指定できるはずだが、型変換のコンバータがうまく呼び出せないため、落ちてしまうことからランダムとなっている。
都度、どのポートを使用しているかの確認が必要。
（作者はlsof -i | grep java で検索している）

2. ログの出力ができない
→おそらく、1.と同じ理由でロガーの呼び出しができていないため、ログを出力させたい際はstdに出力させている。

上記の2つ以外にもあるが、割愛。
原因はCamelContextの呼び出し方、使い方にあるように思料。
追って原因調査、および対応予定。
テストコードもそのタイミングで作成予定。

## 使い方

1. pom.xmlにてお使いの環境に合わせた記載に修正ください。
デフォルトではJDK11、Camel3.20(LTS)で検証しています。
JDK17、Camel4(Beta)でも動作確認しています。

2. お好きなRouteを使用してください
デフォルトではRestRouteがルートとして登録されます。
HelloRouteは対話側の簡単なアプリケーションになります。

3. ビルド後、java -jarで動作します。
オプションは特に不要です。

## 今後について

現時点ではundortowを使用しているため、quarkusでの動作対応などを行う見込です。
また、Uber-jarなど異なる仕組みなどの対応もこのアプリケーションをベースとして検証していく予定です。
コメント等も追って付与していきます。


## ご参考

以下、REST API叩く際にお使いください。
ポート番号の変更が必要です。

curl -X POST -H "Content-Type: application/json" localhost:8888/cameltest/test1
curl -X GET -H "Content-Type: application/json" localhost:8888/cameltest/test2
curl -X GET -H "Content-Type: application/json" localhost:8888/cameltest/test3


curl -X POST -H "Content-Type: application/json" localhost:53496/undertow/test1
curl -X GET -H "Content-Type: application/json" localhost:55262/undertow/test2
curl -X GET -H "Content-Type: application/json" localhost:55262/undertow/test3
curl -X GET -H "Content-Type: application/json" localhost:55262/undertow/test4
