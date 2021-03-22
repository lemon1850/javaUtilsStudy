package cn.catgod.mapstruct;

import org.mapstruct.Mapper;

/**
 * 可用于spring依赖注入
 *
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/19
 */
@Mapper(componentModel = "spring")
public interface SimpleSourceDestinationMapper {

    SimpleDestination sourceToDestination(SimpleSource simpleSource);

    SimpleSource destinationToSource(SimpleDestination simpleDestination);
}
