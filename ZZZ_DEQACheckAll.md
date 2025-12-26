
# DECheckAllの管理

## 非SSH接続作業

**データフォルダ（share_package）のサーバーへの同期（↑）** **削除あり**
```bash
rsync -avhu --delete ./share_package/ /media/iu/homes_on_DS920/iueda/VaadinApps/share_package/
```

**データフォルダ（share_package）のサーバーからの同期（↓）**
```bash
rsync -avhu ./share_package/ /media/iu/homes_on_DS920/iueda/VaadinApps/share_package/
```

**DEQACheck-VaadinApp-*.jarファイルをUpload↑**
```bash
scp -P 22 ./build/libs/DEQACheck-VaadinApp-*.jar iueda@172.16.1.3:/var/services/homes/iueda/VaadinApps/
```

## SSH接続での作業

**DS920へSSH接続**
```bash
ssh -p 22 iueda@172.16.1.3
```

```bash
cd ~/VaadinApps/
ls
```

起動中のVaadinApp確認方法：

```
# DS920
netstat -tlnp | grep 8080
```

解決方法：

1. 既存のプロセスを終了する：
```
# PIDを確認して終了
kill -9 <PID>
```

2. または別のポートで起動する：
```
java -jar DEQACheck-VaadinApp-v20251210.jar --server.port=8081
```