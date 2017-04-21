package bean;

import java.util.List;

public class TransferQualificationtypeInfo {
	private String uid;
	private String name;
	private String strapplicableproblemtypeUids;
	private List<TransferProblemtypeInfo> applicableproblemtypes;
	private TransferAdminInfo createuser;
	private int createtime;
	private String createip;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStrapplicableproblemtypeUids() {
		return strapplicableproblemtypeUids;
	}
	public void setStrapplicableproblemtypeUids(String strapplicableproblemtypeUids) {
		this.strapplicableproblemtypeUids = strapplicableproblemtypeUids;
	}
	public List<TransferProblemtypeInfo> getApplicableproblemtypes() {
		return applicableproblemtypes;
	}
	public void setApplicableproblemtypes(
			List<TransferProblemtypeInfo> applicableproblemtypes) {
		this.applicableproblemtypes = applicableproblemtypes;
	}
	public TransferAdminInfo getCreateuser() {
		return createuser;
	}
	public void setCreateuser(TransferAdminInfo createuser) {
		this.createuser = createuser;
	}
	public int getCreatetime() {
		return createtime;
	}
	public void setCreatetime(int createtime) {
		this.createtime = createtime;
	}
	public String getCreateip() {
		return createip;
	}
	public void setCreateip(String createip) {
		this.createip = createip;
	}
	@Override
	public String toString() {
		return "TransferQualificationtypeInfo [uid=" + uid + ", name=" + name
				+ ", strapplicableproblemtypeUids=" + strapplicableproblemtypeUids
				+ ", applicableproblemtypes=" + applicableproblemtypes
				+ ", createuser=" + createuser + ", createtime=" + createtime
				+ ", createip=" + createip + "]";
	}
}
