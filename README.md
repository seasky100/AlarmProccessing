# AlarmProccessing
告警处理
告警处理APP开发思路
AlarmProcessing:告警处理
Login->showList->proccessing

模式采用MVP。用OkHttp进行网络连接。
登录页面单独写一个LoginAactivity，通过loginPresenter向后台交互。
消息列表采用recyclerView，对应的itemView展示应该采用第三方包。
警情处理和处理反馈界面应该写成一个activity，通过switch切换，这里图片展示可以自己写，如果以后要加入短视频或视频流之类可以再导入第三方。
这里 pickImage采用知乎开源库框架
![image](https://github.com/wangchenhao006/AlarmProccessing/master/AlarmProccessing/Resource/AlarmProccessing.gif)
