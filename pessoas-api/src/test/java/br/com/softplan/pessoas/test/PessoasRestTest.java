package br.com.softplan.pessoas.test;


import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import br.com.softplan.pessoas.PessoasApiApplication;
import br.com.softplan.pessoas.feature.pessoa.repository.PessoaRepository;
import br.com.softplan.pessoas.feature.pessoa.rest.PessoaDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PessoasApiApplication.class)
@WebAppConfiguration
@TestInstance(Lifecycle.PER_CLASS)
public class PessoasRestTest extends AbstractTest {

  String uri = "/api/v1/pessoas";

  @Autowired
  private PessoaRepository repository;

  @Test
  @Order(1)
  public void _1_naoDevePossuirPessoasCadastradas() throws Exception {
    log.info("Teste 1 - Não deve haver nenhum dado cadastrado no banco H2 - Memory");

    Assertions.assertTrue(repository.findAll().size() == 0);
  }

  @Test
  @Order(2)
  public void _2_naoDevePermitirPessoasSemNomeNemCPF() throws Exception {
    log.info("Teste 2 - Não deve permitir inserir sem Nome, CPF e Sexo");
    PessoaDTO pessoa = new PessoaDTO();
    String inputJson = super.mapToJson(pessoa);

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(400, status);
    String content = mvcResult.getResponse().getContentAsString();

    Assertions.assertTrue(StringUtils.contains(content, "Nome da pessoa"));
    Assertions.assertTrue(StringUtils.contains(content, "CPF da pessoa"));
    Assertions.assertTrue(StringUtils.contains(content, "Sexo da pessoa"));
  }

  @Test
  @Order(3)
  public void _3_naoDevePermitirInserirComCPFErrado() throws Exception {
    log.info("Teste 3 - Não deve permitir inserir com CPF errado");

    PessoaDTO pessoa = new PessoaDTO();

    pessoa.setCpf("0286223588113");
    pessoa.setNome("Ruan Carlos Perondi");
    pessoa.setSexo("Masculino");

    String inputJson = super.mapToJson(pessoa);

    log.info(inputJson);

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    String content = mvcResult.getResponse().getContentAsString();
    log.info(content);

    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(400, status);

    Assertions.assertTrue(StringUtils.contains(content, "CPF inválido"));
  }

  @Test
  @Order(4)
  public void _4_devePermitirInserir() throws Exception {
    log.info("Teste 4 - Deve permitir inserir com dados validos");
    PessoaDTO pessoa = new PessoaDTO();

    pessoa.setCpf("02862588113");
    pessoa.setNome("Ruan Carlos Perondi");
    pessoa.setSexo("Masculino");

    String inputJson = super.mapToJson(pessoa);

    log.info(inputJson);

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    String content = mvcResult.getResponse().getContentAsString();
    log.info(content);

    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(200, status);

    Assertions.assertTrue(StringUtils.contains(content, "Ruan Carlos Perondi"));
  }

  @Test
  @Order(5)
  public void _5_naoDevePermitirInserirCPFDuplicado() throws Exception {
    log.info("Teste 5 - Deve permitir inserir com dados validos");
    PessoaDTO pessoa = new PessoaDTO();

    pessoa.setCpf("02862588113");
    pessoa.setNome("Ruan Carlos Perondi");
    pessoa.setSexo("Masculino");

    String inputJson = super.mapToJson(pessoa);

    log.info(inputJson);

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    String content = mvcResult.getResponse().getContentAsString();
    log.info(content);

    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(400, status);

    Assertions.assertTrue(StringUtils.contains(content, "Pessoa ja encontrada com o"));
  }


  @Test
  @Order(6)
  public void _5_naoDevePossuirPessoasCadastradas() throws Exception {
    log.info("Teste 6 - Deve Existir somente um registro na base");

    Assertions.assertTrue(repository.findAll().size() == 1);
  }

  @Test
  @Order(7)
  public void _6_deveAlterarNome() throws Exception {
    log.info("Teste 6 - Alterar um usuário existemte");
    PessoaDTO pessoa = new PessoaDTO();

    pessoa.setCpf("02862588113");
    pessoa.setNome("Ruan Carlos Perondi - Alterado");
    pessoa.setSexo("Masculino");

    String inputJson = super.mapToJson(pessoa);

    log.info(inputJson);

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri + "/02862588113").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    String content = mvcResult.getResponse().getContentAsString();
    log.info(content);

    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(200, status);

    Assertions.assertTrue(StringUtils.contains(content, "Alterado"));
  }

  @Test
  @Order(7)
  public void _7_deveConterApenasUmRegistro() throws Exception {
    log.info("Teste 7 - Deve Existir somente um registro na base");

    Assertions.assertTrue(repository.findAll().size() == 1);
  }

  @Test
  @Order(8)
  public void _8_alteracaoEmCPFInexistente() throws Exception {
    log.info("Teste 8 - Não pode atualizar um registro que não existe");
    PessoaDTO pessoa = new PessoaDTO();

    pessoa.setCpf("17890228007");// CPF não cadastrado
    pessoa.setNome("Ruan Carlos Perondi - Alterado");
    pessoa.setSexo("Masculino");

    String inputJson = super.mapToJson(pessoa);

    log.info(inputJson);

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri + "/17890228007").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    String content = mvcResult.getResponse().getContentAsString();
    log.info(content);

    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(400, status);

    Assertions.assertTrue(StringUtils.contains(content, "Pessoa não encontrada com o CPF"));
  }

  @Test
  @Order(9)
  public void _9_inclusaoDeNovasPessoas() throws Exception {
    log.info("Teste 8 - Não pode atualizar um registro que não existe");
    incluirPessoas("17890228007", "João da Silva", "Masculino");
    incluirPessoas("03410002057", "Maria da Silva", "Feminino");
    incluirPessoas("99830860000", "Geraldo Santos", "Masculino");
  }

  @Test
  @Order(10)
  public void _10_deveRemoverCPFExistente() throws Exception {
    log.info("Teste 10 - Deve remover um usuário (CPF - 02862588113)");
    PessoaDTO pessoa = new PessoaDTO();

    String inputJson = super.mapToJson(pessoa);

    log.info(inputJson);

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri + "/02862588113").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    String content = mvcResult.getResponse().getContentAsString();
    log.info(content);

    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(200, status);
  }
  
  @Test
  @Order(11)
  public void _10_usuarioNaoEncontrado() throws Exception {
    log.info("Teste 11 - Não deve remover um usuário (CPF - 02862588113)");
    PessoaDTO pessoa = new PessoaDTO();

    String inputJson = super.mapToJson(pessoa);

    log.info(inputJson);

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri + "/02862588113").contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    String content = mvcResult.getResponse().getContentAsString();
    log.info(content);

    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(400, status);

    Assertions.assertTrue(StringUtils.contains(content, "Pessoa não encontrada com o CPF"));
  }
  
  @Test
  @Order(12)
  public void _12_deveExistirTresRegistros() throws Exception {
    log.info("Teste 12 - Deve Existir 3 registros na base");

    Assertions.assertTrue(repository.findAll().size() == 3);
  }

  @Test
  @Order(13)
  public void _13_deveListarTodos() throws Exception {
    log.info("Teste 11 - Não deve remover um usuário (CPF - 02862588113)");
    
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    
    String content = mvcResult.getResponse().getContentAsString();
    log.info(content);
    
    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(200, status);
    
    Assertions.assertTrue(StringUtils.contains(content, "\"totalElements\":3"));
  }

  @Test
  @Order(14)
  public void _14_deveListarTodosMasculinos() throws Exception {
    log.info("Teste 11 - Não deve remover um usuário (CPF - 02862588113)");
    
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri+"?sexo=Masculino").contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
    
    String content = mvcResult.getResponse().getContentAsString();
    log.info(content);
    
    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(200, status);
    
    Assertions.assertTrue(StringUtils.contains(content, "\"totalElements\":2"));
  }
  
  private void incluirPessoas(String cpf, String nome, String sexo) throws Exception {
    PessoaDTO pessoa = new PessoaDTO();

    pessoa.setCpf(cpf);
    pessoa.setNome(nome);
    pessoa.setSexo(sexo);

    String inputJson = super.mapToJson(pessoa);

    log.info(inputJson);

    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

    String content = mvcResult.getResponse().getContentAsString();
    log.info(content);

    int status = mvcResult.getResponse().getStatus();
    Assertions.assertEquals(200, status);
  }

  @Override
  @BeforeAll
  public void setUp() {
    super.setUp();
  }
}
