package com.example.samsung.magicianbackend.sample;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.samsung.magicianbackend.MagicianBackendApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@SpringBootTest(classes = MagicianBackendApplication.class)
public class SampleControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    private RestDocumentationResultHandler document;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.document = document("{class-name}/{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));
    	this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentation))
            .alwaysDo(document)
            .build();
    }
  
	@Test
	public void getSample() throws Exception {
		mockMvc.perform(get("/sample/{id}",1L)
				.contentType(MediaTypes.ALPS_JSON).characterEncoding("UTF-8"))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(document.document(
							pathParameters(
								parameterWithName("id").description("sample's id")
							),
							responseFields(
								fieldWithPath("id").description("sample's id"),
								fieldWithPath("name").description("sample's name"),
								fieldWithPath("description").description("sample's description"),
								fieldWithPath("position").description("sample's position"),
								fieldWithPath("age").description("sample's age"),
								fieldWithPath("createdAt").description("created at"),
								fieldWithPath("updateAt").description("update at")
							)
				))
				.andExpect(jsonPath("$.name", is(notNullValue())));
	}
  
	@Test
	public void getSamples() throws Exception {
		mockMvc.perform(get("/sample/list?size=3&page=1&direction=ASC")
				.contentType(MediaTypes.ALPS_JSON).characterEncoding("UTF-8"))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(document.document(
							requestParameters(
								parameterWithName("size").description("page size"),
								parameterWithName("page").description("page number"),
								parameterWithName("direction").description("sort direction")
							),
							responseFields(
								fieldWithPath("content.[].id").description("sample's id"),
								fieldWithPath("content.[].name").description("sample's name"),
								fieldWithPath("content.[].description").description("sample's description"),
								fieldWithPath("content.[].position").description("sample's position"),
								fieldWithPath("content.[].age").description("sample's age"),
								fieldWithPath("content.[].createdAt").description("created at"),
								fieldWithPath("content.[].updateAt").description("update at"),
								fieldWithPath("pageable.sort.sorted").description(".."),
								fieldWithPath("pageable.sort.unsorted").description(".."),
								fieldWithPath("pageable.sort.empty").description(".."),
								fieldWithPath("pageable.offset").description(".."),
								fieldWithPath("pageable.pageSize").description(".."),
								fieldWithPath("pageable.pageNumber").description(".."),
								fieldWithPath("pageable.paged").description(".."),
								fieldWithPath("pageable.unpaged").description(".."),
								fieldWithPath("last").description(".."),
								fieldWithPath("totalPages").description(".."),
								fieldWithPath("totalElements").description(".."),
								fieldWithPath("size").description(".."),
								fieldWithPath("number").description(".."),
								fieldWithPath("first").description(".."),
								fieldWithPath("numberOfElements").description(".."),
								fieldWithPath("sort.sorted").description(".."),
								fieldWithPath("sort.unsorted").description(".."),
								fieldWithPath("sort.empty").description(".."),
								fieldWithPath("empty").description("..")
							)
				));
	}
	
	@Test
	public void createSample() throws Exception {
		
		Map<String, Object> sample = new HashMap<>();
		sample.put("name", "hong");
		sample.put("age", 23);
		sample.put("description", "test");
		sample.put("position", "CL1");
		
		mockMvc.perform(put("/sample")
				.contentType(MediaTypes.ALPS_JSON).characterEncoding("UTF-8")
				.content(this.objectMapper.writeValueAsString(sample)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andDo(document.document(
							requestFields(
									fieldWithPath("name").description("sample's name"),
									fieldWithPath("description").description("sample's description"),
									fieldWithPath("position").description("sample's position"),
									fieldWithPath("age").description("sample's age")
							))
				);
	}
	
//	@Test
//	public void deleteSample() throws Exception {
//		
//		mockMvc.perform(delete("/sample/{id}",11)
//				.contentType(MediaTypes.ALPS_JSON))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andDo(document("sample-delete-example",
//							pathParameters(
//								parameterWithName("id").description("sample's id")
//							))
//				);
//	}
  
}