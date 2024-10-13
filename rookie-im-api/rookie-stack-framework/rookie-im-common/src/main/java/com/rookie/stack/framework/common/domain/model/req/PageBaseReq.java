package com.rookie.stack.framework.common.domain.model.req;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * @author eumenides
 * @description
 * @date 2024/10/13
 */
@Data
public class PageBaseReq {


    @Min(0)
    @Max(50)
    private Integer pageSize = 10;

    private Integer pageNo = 1;

    /**
     * 获取mybatisPlus的page
     *
     * @return
     */
    public Page plusPage() {
        return new Page(pageNo, pageSize);
    }
}
