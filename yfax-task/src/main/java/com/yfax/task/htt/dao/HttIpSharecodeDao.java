package com.yfax.task.htt.dao;

import java.util.List;

import com.yfax.task.htt.vo.HttIpSharecodeVo;

public interface HttIpSharecodeDao {

	public List<HttIpSharecodeVo> selectHttIpSharecodeUnFinished();

	public Boolean batchUpdateUnFinished(List<HttIpSharecodeVo> list);

}
