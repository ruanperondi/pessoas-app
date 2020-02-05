package br.com.softplan.pessoas.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operação realizada com sucesso"),
        @ApiResponse(code = 404, message = "Recurso não encontrado"), 
        @ApiResponse(code = 403, message = "Operação não permitida"),
        @ApiResponse(code = 500, message = "ISE") 
})
public @interface DefaultApiResponse {
}