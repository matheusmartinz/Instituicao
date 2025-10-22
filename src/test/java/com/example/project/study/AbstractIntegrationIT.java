package com.example.project.study;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;

@SpringBootTest(classes = StudyApplication.class)
@ActiveProfiles("test")
public class AbstractIntegrationIT extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;

    @SneakyThrows
    @BeforeMethod
    protected void beforeMethod() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @SneakyThrows
    public ResultActions getRequest(String url, ResultMatcher mockMvcResultMatchersStatusExpected) {
        return mvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(result -> {
            if (result.getResolvedException() != null) {
                result.getResolvedException().printStackTrace();
            }
        }).andExpect(mockMvcResultMatchersStatusExpected);
    }

    @SneakyThrows
    public ResultActions postRequest(String url, String content, ResultMatcher mockMvcResultMatchersStatusExpected) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        if (content != null) {
            requestBuilder = requestBuilder.content(content);
        }
        return mvc.perform(requestBuilder).andDo(result -> {
            if (result.getResolvedException() != null) {
                result.getResolvedException().printStackTrace();
            }
        }).andExpect(mockMvcResultMatchersStatusExpected);
    }

    @SneakyThrows
    public ResultActions deleteRequest(String url, ResultMatcher mockMvcResultMatchersStatusExpected) {
        return mvc.perform(MockMvcRequestBuilders.delete(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(mockMvcResultMatchersStatusExpected);
    }

    @SneakyThrows
    public ResultActions putRequest(String url, String content, ResultMatcher mockMvcResultMatchersStatusExpected) {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(url)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        if (content != null) {
            requestBuilder = requestBuilder.content(content);
        }

        return mvc.perform(requestBuilder).andDo(result -> {
            if (result.getResolvedException() != null) {
                result.getResolvedException().printStackTrace();
            }
        }).andExpect(mockMvcResultMatchersStatusExpected);
    }

}

//import br.com.produtec.nexus.domain.model.auditoria.AuditoriaRepository;
//import br.com.produtec.nexus.domain.model.estabelecimento.EstabelecimentoDataProvider;
//import br.com.produtec.nexus.domain.model.estabelecimento.EstabelecimentoRedRepository;
//import br.com.produtec.nexus.domain.model.estabelecimento.EstabelecimentoRepository;
//import br.com.produtec.nexus.domain.model.usuario.UsuarioDataProvider;
//import br.com.produtec.nexus.infrastructure.context.ApiContext;
//import br.com.produtec.nexus.infrastructure.context.ApiContextThreadScope;
//import br.com.produtec.nexus.view.session.SessionData;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.binding.message.DefaultMessageContext;
//import org.springframework.binding.message.MessageContext;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.context.WebApplicationContext;
//import org.testng.annotations.BeforeMethod;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.io.IOException;
//
//@SpringBootTest(classes = Application.class)
//@ActiveProfiles(profiles = {"vagrant"})
//@ContextConfiguration(classes = {Application.class, UsuarioTestConfig.class})
//public abstract class AbstractIntegrationTest extends AbstractTransactionalTestNGSpringContextTests {
//
//    @PersistenceContext
//    protected EntityManager entityManager;
//
//    @Autowired
//    protected AuditoriaRepository auditoriaRepository;
//
//    @Autowired
//    protected EstabelecimentoRepository estabelecimentoRepository;
//
//    @Autowired
//    protected EstabelecimentoRedRepository estabelecimentoRedRepository;
//
//    protected MessageContext messageContext;
//
//    protected JPAQueryFactory newQuery() {
//        return new JPAQueryFactory(entityManager);
//    }
//
//    protected final SessionData sessionData = SessionData.of(UsuarioDataProvider.produtec(), EstabelecimentoDataProvider.produtec());
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//    private MockMvc mvc;
//
//    @Autowired
//    protected ObjectMapper objectMapper;
//
//    @SneakyThrows
//    @BeforeMethod
//    protected void beforeMethod() {
//        ContextMocker.mockFacesContextWithUserSession();
//        this.mvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
//        messageContext = new DefaultMessageContext();
//        setApiContext();
//    }
//
//    protected void entityManager_flushClear() {
//        entityManager.flush();
//        entityManager.clear();
//    }
//
//    // MUITO CUIDADO! QUANDO DA REFRESH NA VIEW, ELE COMMITA AS TRANSAÇÕES
//    protected void refreshView(String viewName) {
//        entityManager.createNativeQuery("BEGIN\n" +
//                "    DBMS_MVIEW.REFRESH('" + viewName + "');\n" +
//                "END;"
//        ).executeUpdate();
//    }
//
//    public SessionData getSessionData() {
//        return SessionData.of(UsuarioDataProvider.produtec(), EstabelecimentoDataProvider.produtec());
//    }
//
//    public void setApiContext() throws IOException {
//        ApiContextThreadScope.getInstance().setApiContext(new ApiContext(UsuarioDataProvider.produtec(), EstabelecimentoDataProvider.produtec(),
//                JWTPayloadDataProvider.superPayload()));
//    }
//
//    public ApiContext getApiContext() {
//        return ApiContextThreadScope.getInstance().getApiContext();
//    }
//
//    public String toJsonString(Object obj) {
//        try {
//            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
//        } catch (Exception e) {
//            System.out.println("Não foi possível converter o obj para String => " + e);
//        }
//        return null;
//    }
//
//    @SneakyThrows
//    public ResultActions getRequest(String url, String token, ResultMatcher mockMvcResultMatchersStatusExpected) {
//        return mvc.perform(MockMvcRequestBuilders.get(url)
//                .header("access_token", token)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)).andDo(result -> {
//            if (result.getResolvedException() != null) {
//                result.getResolvedException().printStackTrace();
//            }
//        }).andExpect(mockMvcResultMatchersStatusExpected);
//    }
//
//    @SneakyThrows
//    public ResultActions getRequest(String url, String token, ResultMatcher mockMvcResultMatchersStatusExpected,
//                                    MultiValueMap<String, String> params) {
//        return mvc.perform(MockMvcRequestBuilders.get(url)
//                .header("access_token", token)
//                .params(params)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)).andDo(result -> {
//            if (result.getResolvedException() != null) {
//                result.getResolvedException().printStackTrace();
//            }
//        }).andExpect(mockMvcResultMatchersStatusExpected);
//    }
//
//    @SneakyThrows
//    public ResultActions postRequest(String url, String token, String content, ResultMatcher mockMvcResultMatchersStatusExpected) {
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
//                .header("access_token", token)
//                .characterEncoding("UTF-8")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON);
//
//        if (content != null) {
//            requestBuilder = requestBuilder.content(content);
//        }
//
//        return mvc.perform(requestBuilder).andDo(result -> {
//            if (result.getResolvedException() != null) {
//                result.getResolvedException().printStackTrace();
//            }
//        }).andExpect(mockMvcResultMatchersStatusExpected);
//    }
//
//    @SneakyThrows
//    public ResultActions postRequest(String url, String token, String content,
//                                     ResultMatcher mockMvcResultMatchersStatusExpected,
//                                     MultiValueMap<String, String> params) {
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
//                .header("access_token", token)
//                .params(params)
//                .characterEncoding("UTF-8")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON);
//
//        if (content != null) {
//            requestBuilder = requestBuilder.content(content);
//        }
//
//        return mvc.perform(requestBuilder).andDo(result -> {
//            if (result.getResolvedException() != null) {
//                result.getResolvedException().printStackTrace();
//            }
//        }).andExpect(mockMvcResultMatchersStatusExpected);
//    }
//
//    @SneakyThrows
//    public ResultActions postRequest(String url, String token, String content,
//                                     ResultMatcher mockMvcResultMatchersStatusExpected,
//                                     MultiValueMap<String, String> params, MediaType contentType, MediaType accept) {
//        contentType = contentType != null ? contentType : MediaType.APPLICATION_JSON;
//        accept = accept != null ? accept : MediaType.APPLICATION_JSON;
//
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url)
//                .header("access_token", token)
//                .params(params)
//                .characterEncoding("UTF-8")
//                .contentType(contentType)
//                .accept(accept);
//
//        if (content != null) {
//            requestBuilder = requestBuilder.content(content);
//        }
//
//        return mvc.perform(requestBuilder).andDo(result -> {
//            if (result.getResolvedException() != null) {
//                result.getResolvedException().printStackTrace();
//            }
//        }).andExpect(mockMvcResultMatchersStatusExpected);
//    }
//
//    @SneakyThrows
//    public ResultActions putRequest(String url, String token, String content, ResultMatcher mockMvcResultMatchersStatusExpected) {
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(url)
//                .header("access_token", token)
/// /                .locale(Locale."pt-BR")
//                .characterEncoding("UTF-8")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON);
//
//        if (content != null) {
//            requestBuilder = requestBuilder.content(content);
//        }
//
//        return mvc.perform(requestBuilder).andDo(result -> {
//            if (result.getResolvedException() != null) {
//                result.getResolvedException().printStackTrace();
//            }
//        }).andExpect(mockMvcResultMatchersStatusExpected);
//    }
//
//    @SneakyThrows
//    public ResultActions putRequest(String url, String token, String content,
//                                    ResultMatcher mockMvcResultMatchersStatusExpected,
//                                    MultiValueMap<String, String> params) {
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put(url)
//                .header("access_token", token)
//                .params(params)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON);
//
//        if (content != null) {
//            requestBuilder = requestBuilder.content(content);
//        }
//        return mvc.perform(requestBuilder).andDo(result -> {
//            if (result.getResolvedException() != null) {
//                result.getResolvedException().printStackTrace();
//            }
//        }).andExpect(mockMvcResultMatchersStatusExpected);
//    }
//
//    @SneakyThrows
//    public ResultActions deleteRequest(String url, String token, ResultMatcher mockMvcResultMatchersStatusExpected) {
//        return mvc.perform(MockMvcRequestBuilders.delete(url)
//                        .header("access_token", token)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(mockMvcResultMatchersStatusExpected);
//    }
//
//    @SneakyThrows
//    public ResultActions deleteRequest(String url, String token, String content, ResultMatcher mockMvcResultMatchersStatusExpected) {
//        return mvc.perform(MockMvcRequestBuilders.delete(url)
//                        .header("access_token", token)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(mockMvcResultMatchersStatusExpected);
//    }
//
//    @SneakyThrows
//    public ResultActions patchRequest(String url, String token, ResultMatcher mockMvcResultMatchersStatusExpected) {
//        return mvc.perform(MockMvcRequestBuilders.patch(url)
//                        .header("access_token", token)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(mockMvcResultMatchersStatusExpected);
//    }
//
//    @SneakyThrows
//    public ResultActions patchRequest(String url, String token, String content, ResultMatcher mockMvcResultMatchersStatusExpected) {
//        return mvc.perform(MockMvcRequestBuilders.patch(url)
//                        .header("access_token", token)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(mockMvcResultMatchersStatusExpected);
//    }
//
//    public MultiValueMap<String, String> createRequestParams(String... params) {
//        if (params.length % 2 != 0)
//            throw new IllegalArgumentException("A quantidade de parâmetros deve ser múltipla de dois.");
//
//        MultiValueMap<String, String> toReturn = new LinkedMultiValueMap<>();
//        for (int i = 0; i < params.length; i += 2) {
//            String key = params[i];
//            String value = params[i + 1];
//            toReturn.add(key, value);
//        }
//        return toReturn;
//    }
//
//    //gamber para evitar conflito de nomes por enquantoa
//    protected ObjectMapper getMapper() {
//        return objectMapper;
//    }
//
//}
