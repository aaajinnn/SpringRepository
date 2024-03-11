package common.domain;

public class SampleVO {

	private String tname;
	private String tabType;

	// 기본생성자 자동으로 만들어줘서 얘는 생략해도됨
	public SampleVO() {
		System.out.println("SampleVO() 생성자");
	}
	
	//setter, getter
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTabType() {
		return tabType;
	}
	public void setTabType(String tabType) {
		this.tabType = tabType;
	}
	
	
}
