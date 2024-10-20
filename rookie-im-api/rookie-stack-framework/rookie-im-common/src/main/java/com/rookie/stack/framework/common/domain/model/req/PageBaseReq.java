package com.rookie.stack.framework.common.domain.model.req;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * @author eumenides
 * @description
 * @date 2024/10/13
 */
@Data
@Schema(description = "基础分页请求")
public class PageBaseReq {


    @Min(0)
    @Max(50)
    @Schema(description = "页面大小")
    private Integer pageSize = 10;
    @Schema(description = "页面索引（从 1 开始）")
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
