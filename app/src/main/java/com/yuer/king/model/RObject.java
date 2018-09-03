package com.yuer.king.model;


import android.graphics.Paint;

public class RObject {
	static final MyInputType[] sStyleArray = {
			MyInputType.Type1,MyInputType.Type2,MyInputType.Type3,MyInputType.Type4
	};
	//private MyInputType myInputType = MyInputType.Type1;

	private String objectRule = "#";// 匹配规则
	private String objectText;// 高亮文本

	public String getObjectRule() {
		return objectRule;
	}

	public void setObjectRule(String objectRule) {
		this.objectRule = objectRule;
	}
	public void setObjectRule(MyInputType myInputType) {
		this.objectRule = myInputType.toString();
	}

	public String getObjectText() {
		return objectText;
	}

	public void setObjectText(String objectText) {
		this.objectText = objectText;
	}



	public enum MyInputType{

		Type1 ("#"), Type2 ("@"), Type3 ("$"),Type4 ("#");
		 private  String charType = "#";


		// 构造函数，枚举类型只能为私有
        MyInputType(String charType) {
			this.charType = charType;
		}

		public String getCharType(){
			return this.charType;
		}
//
		public void setCharType(String charType){
			this.charType=charType;
		}

		@Override
		public String toString() {
			return this.charType;
		}
	}
}
