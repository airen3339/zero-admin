package ${basePackageName}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${basePackageName}.entity.${entityName};
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * ${comment}的Mapper接口
 *
 * @author ${codeAuthor}
 * @since ${.now?string("yyyy-MM-dd HH:mm")}
 */
@Mapper
@Repository
public interface ${entityName}Mapper extends BaseMapper<${entityName}> {

    /**
     * 分页获取用户数据，区别于selectPage的是，该方法添加了查询条件
     *
     * @param page      分页查询
     * @param query${entityName} ${entityName}查询条件
     * @return ${entityName}集合
     * @throws Exception 抛出异常
     */
    IPage<${entityName}> getPage(IPage page, @Param("query${entityName}") ${entityName} query${entityName}) throws Exception;

}