package com.sbs.dagachi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbs.dagachi.vo.ShareArticle;

@Mapper
public interface ShareArticleRepository {

	public List<ShareArticle> getBoardId2(int limitStart, int limitTake, String searchKeywordTypeCode, String searchKeyword);

	public int getArticleboardId2Count(String searchKeywordTypeCode, String searchKeyword);
	
	public List<ShareArticle> getarticlebyarticleid(String article_id);
	
	public void articleModify(@Param("article_title")String article_title,@Param("article_body")String article_body,
								@Param("article_attach")String article_attach,@Param("article_id")String article_id);
	
	public void articleDelete(@Param("article_id")String article_id);
	public void articleWrite(@Param("article_title") String article_title, @Param("article_body") String article_body,
							@Param("article_attach") String article_attach, @Param("article_register") String article_register);
}
