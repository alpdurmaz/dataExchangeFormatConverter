package com.converter.dependencyConverter.presentation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturn_home(){

        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/"
                , String.class, contains("home")));
    }

    @Test
    public void shouldReturn_convertDependency(){

        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/convert-dependency"
                , String.class, contains("convertDependency")));
    }

    @Test
    public void shouldReturn_jsonToXml(){

        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "json-to-xml"
                , String.class, contains("jsonToXml")));
    }

    @Test
    public void shouldReturn_jsonToYaml(){

        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "json-to-yaml"
                , String.class, contains("jsonToYaml")));
    }

    @Test
    public void shouldReturn_xmlToJson(){

        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "xml-to-json"
                , String.class, contains("xmlToJson")));
    }

    @Test
    public void shouldReturn_xmlToYaml(){

        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "xml-to-yaml"
                , String.class, contains("xmlToYaml")));
    }

    @Test
    public void shouldReturn_yamlToJson(){

        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "yaml-to-json"
                , String.class, contains("yamlToJson")));
    }

    @Test
    public void shouldReturn_yamlToXml(){

        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "yaml-to-xml"
                , String.class, contains("yamlToXml")));
    }

    @Test
    public void shouldReturn_contact(){

        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "contact"
                , String.class, contains("contact")));
    }
}
