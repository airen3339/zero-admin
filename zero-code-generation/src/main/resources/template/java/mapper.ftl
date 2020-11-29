package ${basePackageName}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import ${basePackageName}.entity.${entityName};
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
     * 分页查询${comment}数据，区别于selectPage的是，该方法添加了查询条件
     *
     * @param page      分页查询
     * @param query${entityName} ${entityName}查询条件
     * @return ${entityName}集合
     * @throws Exception 抛出异常
     */
    IPage<${entityName}> getPage(IPage page, @Param("query${entityName}") ${entityName} query${entityName}) throws Exception;


    /**
     * 分页查询逻辑删除的${comment}数据
     *
     * @param page      分页查询
     * @param query${entityName} ${entityName}查询条件
     * @return ${entityName}集合
     * @throws Exception 抛出异常
     */
    IPage<${entityName}> getDeletePage(IPage page, @Param("query${entityName}") ${entityName} query${entityName}) throws Exception;


    /**
     * 获取所有的${comment}数据，区别于selectList的是，该方法添加了查询条件
     *
     * @param query${entityName} ${entityName}查询条件
     * @return ${entityName}集合
     * @throws Exception 抛出异常
     */
    List<${entityName}> getList(@Param("query${entityName}") ${entityName} query${entityName}) throws Exception;

}
