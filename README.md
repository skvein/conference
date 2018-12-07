# conference
设计：使用面向对象的编程方式，会议信息存放在txt文件中
  conference(会议对象) 包含属性：会议名称，会议时长
  track(跟踪对象) 包含属性：名称，跟踪明细
  trackDetai(跟踪明细)  包含属性： 会议开始时间，会议对象（conference）
  basicSetting(基本信息)  包含属性：最小会议时长(lightning),早上开始时间，早上结束时间，下午开始时间，下午结束时间
                         提供方式：设置基本信息，可以修改上述属性信息
  ConferenceService(会议安排接口) 提供方法：初始化会议信息，构建会议安排
  
  ConferenceMain 运行主函数，包含会议基本信息的初始化，和会议安排，并输出安排后的会议信息

会议安排算法：计算出早上/下午总的会议时长（目标数），然后遍历会议信息，递归调用获取到累加时长和目标数一直的会议信息


