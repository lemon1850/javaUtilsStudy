package cn.catgod.lombok;

import lombok.experimental.Delegate;

/**
 * @author weiqiang.lin
 * @version 1.0
 * @date 2021/3/17
 */
public class User implements HasContractInformation{

    @Delegate(types = {HasContractInformation.class})
    private final ContactInformationSupport contactInformationSupport =
            new ContactInformationSupport();
}
