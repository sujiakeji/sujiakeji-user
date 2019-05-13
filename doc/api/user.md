```
curl -v -XPOST 'http://localhost:10200/user/create' \
  --header "Content-Type: application/json" \
  --data '{
      "userNo": "用户编号", 
      "userName": "登录名",
      "passWord": "登录密码",
      "fullName": "真实姓名",
      "sex": "性别",
      "address": "地址",
      "credType": "证件类型",
      "credNum": "证件号码",
      "phone": "手机号码",
      "email": "电子邮箱",
      "country": "国家",
      "profession": "职业",
      "idCardFront": "身份证正面照",
      "idCardBack": "身份证反面照",
      "idCardHand": "手持身份证照",
      "otherData": "其他资料",
      "status": "状态",
      "createBy": 123,
      "isAdmin": "1"
    }'

curl -v -G 'http://localhost:10200/user/getById' \
  --data-urlencode 'id=1'

curl -v -G 'http://localhost:10200/user/get' \
  --data-urlencode 'filters=[
      {"field": "id", "operator": "=", "value": 1}
  ]'

curl -v -G 'http://localhost:10200/user/count' \
  --data-urlencode 'filters=[
  ]'

curl -v -G 'http://localhost:10200/user/list' \
  --data-urlencode 'page=0' \
  --data-urlencode 'size=2' \
  --data-urlencode 'order=-createDate' \
  --data-urlencode 'filters=[
  ]'

curl -v -XPOST 'http://localhost:10200/user/update' \
  --header "Content-Type: application/json" \
  --data '{
      "id": 1
    }'

curl -v -XPOST 'http://localhost:10200/user/delete' \
  --data-urlencode 'filters=[
      {"field": "id", "operator": "=", "value": 1}
  ]'
```