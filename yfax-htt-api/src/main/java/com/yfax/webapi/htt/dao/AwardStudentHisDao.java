package com.yfax.webapi.htt.dao;

import java.util.List;
import com.yfax.webapi.htt.vo.AwardStudentHisVo;

public interface AwardStudentHisDao {
	public List<AwardStudentHisVo> selectAwardStudentHisList(String phoneNum);

	public boolean insertAwardStudentHis(AwardStudentHisVo awardStudentHisVo) throws Exception;
}
