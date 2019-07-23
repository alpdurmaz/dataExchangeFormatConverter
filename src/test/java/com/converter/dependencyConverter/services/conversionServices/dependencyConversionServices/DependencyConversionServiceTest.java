package com.converter.dependencyConverter.services.dependencyConversionServices;

import com.converter.dependencyConverter.services.conversionServices.dependencyConversionServices.DependencyConversionService;
import com.converter.dependencyConverter.services.conversionServices.dependencyConversionServices.GradleConversionService;
import com.converter.dependencyConverter.services.conversionServices.dependencyConversionServices.MavenConversionService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class DependencyConversionServiceTest {
    private GradleConversionService mockGradleConversionService;
    private MavenConversionService mockMavenConversionService;
    private DependencyConversionService testDependencyConversionService;
    private static final String gradleString = "compile group: 'com.amazonaws', name: 'aws-java-sdk', version: '1.11.586'";
    private static final String mavenString = "<dependency>\n" +
            "    <groupId>com.amazonaws</groupId>\n" +
            "    <artifactId>aws-java-sdk-core</artifactId>\n" +
            "    <version>1.11.586</version>\n" +
            "</dependency>";

    @Before
    public void setUp(){

        mockGradleConversionService = mock(GradleConversionService.class);
        mockMavenConversionService = mock(MavenConversionService.class);
        testDependencyConversionService = new DependencyConversionService(mockMavenConversionService, mockGradleConversionService);
    }

    @Test
    public void whenMethodCallWithGradleFormatShouldCallGradleService(){

        testDependencyConversionService.convertDependency(gradleString);

        verify(mockGradleConversionService, times(1)).convertDependency(gradleString);
    }

    @Test
    public void whenMethodCallWithMavenFormatShouldCallMavenService(){

        testDependencyConversionService.convertDependency(mavenString);

        verify(mockMavenConversionService, times(1)).convertDependency(mavenString);
    }
}
