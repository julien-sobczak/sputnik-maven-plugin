/*
 * Copyright 2014 Ingwar & co..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eu.ingwar.maven.sputnik;

import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Parameter;

import pl.touk.sputnik.configuration.Configuration;
import pl.touk.sputnik.configuration.ConfigurationBuilder;
import pl.touk.sputnik.configuration.ConfigurationOption;
import pl.touk.sputnik.configuration.GeneralOption;
import pl.touk.sputnik.connector.ConnectorFacade;
import pl.touk.sputnik.connector.ConnectorFacadeFactory;
import pl.touk.sputnik.connector.ConnectorType;
import pl.touk.sputnik.engine.Engine;

/**
 * Abstract Mojo for all sputnik connectors.
 * 
 * @author Karol Lassak 'Ingwar'
 */
public abstract class SputnikAbstractMojo extends AbstractMojo {

    @Parameter(property = "sputnik.connector.host")
    private String connectorHost;
    
    @Parameter(property = "sputnik.connector.port")
    private String connectorPort;
    
    @Parameter(property = "sputnik.connector.path")
    private String connectorPath;
    
    @Parameter(property = "sputnik.connector.useHttps")
    private String connectorUseHttps;
    
    @Parameter(property = "sputnik.connector.username")
    private String connectorUsername;

    @Parameter(property = "sputnik.connector.password")
    private String connectorPassword;
    
    @Parameter(property = "sputnik.global.processTestFiles")
    private String processTestFiles;
    
    @Parameter(property = "sputnik.global.maxNumberOfComments")
    private String maxNumberOfComments;
    
    @Parameter(property = "sputnik.global.commentOnlyChangedLines")
    private String commentOnlyChangedLines;
    
    @Parameter(property = "sputnik.checkstyle.enabled")
    private String checkstyleEnabled;
    
    @Parameter(property = "sputnik.checkstyle.configurationFile")
    private String checkstyleConfigurationFile;
    
    @Parameter(property = "sputnik.pmd.enabled")
    private String pmdEnabled;

    @Parameter(property = "sputnik.pmd.pmdRulesets")
    private String pmdRulesets;

    @Parameter(property = "sputnik.findbugs.enabled")
    private String findbugsEnabled;

    @Parameter(property = "sputnik.findbugs.includeFilter")
    private String findbugsIncludeFilter;

    @Parameter(property = "sputnik.findbugs.excludeFilter")
    private String findbugsExcludeFilter;

    @Parameter(property = "sputnik.scalastyle.enabled")
    private String scalastyleEnabled;

    @Parameter(property = "sputnik.scalastyle.configurationFile")
    private String scalastyleConfigurationFile;

    @Parameter(property = "sputnik.codenarc.enabled")
    private String codeNarcEnabled;

    @Parameter(property = "sputnik.codenarc.ruleSets")
    private String codeNarcRuleset;
    
    @Parameter(property = "sputnik.codenarc.excludes")
    private String codeNarcExcludes;

    @Parameter(property = "sputnik.jslint.enabled")
    private String jslintEnabled;

    @Parameter(property = "sputnik.jshint.enabled")
    private String jshintEnabled;

    @Parameter(property = "sputnik.jshint.configurationFile")
    private String jshintConfigurationFile;
    
    @Parameter(property = "sputnik.sonar.enabled")
    private String sonarEnabled;

    @Parameter(property = "sputnik.sonar.configurationFiles")
    private String sonarProperties;

    @Parameter(property = "sputnik.sonar.verbose")
    private String sonarVerbose;
   
    @Parameter(property = "sputnik.score.strategy")
    private String scoreStrategy;

    @Parameter(property = "sputnik.score.passingKey")
    private String scorePassingKey;

    @Parameter(property = "sputnik.score.passingValue")
    private String scorePassingValue;

    @Parameter(property = "sputnik.score.failingKey")
    private String scoreFailingKey;

    @Parameter(property = "sputnik.score.failingValue")
    private String scoreFailingValue;
  
    
    private Properties sputnikProperties = new Properties();

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        setConnectorProperty(GeneralOption.CONNECTOR_TYPE, getConnectorType().getName());

        setConnectorProperty(GeneralOption.HOST, connectorHost);
        setConnectorProperty(GeneralOption.PORT, connectorPort);
        setConnectorProperty(GeneralOption.PATH, connectorPath);
        setConnectorProperty(GeneralOption.USE_HTTPS, connectorUseHttps);
        setConnectorProperty(GeneralOption.USERNAME, connectorUsername);
        setConnectorProperty(GeneralOption.PASSWORD, connectorPassword);
        
        setConnectorProperty(GeneralOption.PROCESS_TEST_FILES, processTestFiles);
        setConnectorProperty(GeneralOption.MAX_NUMBER_OF_COMMENTS, maxNumberOfComments);
        setConnectorProperty(GeneralOption.COMMENT_ONLY_CHANGED_LINES, commentOnlyChangedLines);

        setConnectorProperty(GeneralOption.CHECKSTYLE_ENABLED, checkstyleEnabled);
        setConnectorProperty(GeneralOption.CHECKSTYLE_CONFIGURATION_FILE, checkstyleConfigurationFile);

        setConnectorProperty(GeneralOption.PMD_ENABLED, pmdEnabled);
        setConnectorProperty(GeneralOption.PMD_RULESETS, pmdRulesets);

        setConnectorProperty(GeneralOption.FINDBUGS_ENABLED, findbugsEnabled);
        setConnectorProperty(GeneralOption.FINDBUGS_EXCLUDE_FILTER, findbugsExcludeFilter);
        setConnectorProperty(GeneralOption.FINDBUGS_INCLUDE_FILTER, findbugsIncludeFilter);

        setConnectorProperty(GeneralOption.SCALASTYLE_ENABLED, scalastyleEnabled);
        setConnectorProperty(GeneralOption.SCALASTYLE_CONFIGURATION_FILE, scalastyleConfigurationFile);
        
        setConnectorProperty(GeneralOption.CODE_NARC_ENABLED, codeNarcEnabled);
        setConnectorProperty(GeneralOption.CODE_NARC_RULESET, codeNarcRuleset);
        setConnectorProperty(GeneralOption.CODE_NARC_EXCLUDES, codeNarcExcludes);
        
        setConnectorProperty(GeneralOption.JSLINT_ENABLED, jslintEnabled);
        setConnectorProperty(GeneralOption.JSHINT_ENABLED, jshintEnabled);
        setConnectorProperty(GeneralOption.JSHINT_CONFIGURATION_FILE, jshintConfigurationFile);
        
        setConnectorProperty(GeneralOption.SONAR_ENABLED, sonarEnabled);
        setConnectorProperty(GeneralOption.SONAR_PROPERTIES, sonarProperties);
        setConnectorProperty(GeneralOption.SONAR_VERBOSE, sonarVerbose);
       
        setConnectorProperty(GeneralOption.SCORE_STRATEGY, scoreStrategy);
        setConnectorProperty(GeneralOption.SCORE_PASSING_KEY, scorePassingKey);
        setConnectorProperty(GeneralOption.SCORE_PASSING_VALUE, scorePassingValue);
        setConnectorProperty(GeneralOption.SCORE_FAILING_KEY, scoreFailingKey);
        setConnectorProperty(GeneralOption.SCORE_FAILING_VALUE, scoreFailingValue);
        
        System.out.println("Score strategy=" + scoreStrategy);
      
        
        setConnectorProperties();

        Configuration configuration = ConfigurationBuilder.initFromProperties(sputnikProperties);
        ConnectorFacade facade = ConnectorFacadeFactory.INSTANCE.build(getConnectorType(), configuration);
        new Engine(facade, configuration).run();

    }
    
    protected void setConnectorProperty(ConfigurationOption opt, String value) {
        if (value != null) {
            sputnikProperties.setProperty(opt.getKey(), value);
        }
    }

    protected abstract ConnectorType getConnectorType();
    protected abstract void setConnectorProperties();
}
