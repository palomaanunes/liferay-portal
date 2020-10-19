# Styling Checks

Check | File Extensions | Description
----- | --------------- | -----------
[AnnotationUseStyleCheck](https://checkstyle.sourceforge.io/config_annotation.html#AnnotationUseStyle) | .java | Checks the style of elements in annotations. |
AppendCheck | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks instances where literal Strings are appended. |
[ArrayTypeStyleCheck](https://checkstyle.sourceforge.io/config_misc.html#ArrayTypeStyle) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks the style of array type definitions. |
AssertEqualsCheck | .java | Checks that additional information is provided when calling `Assert.assertEquals`. |
AttributeOrderCheck | .java | Checks that attributes in anonymous classes are ordered alphabetically. |
[AvoidNestedBlocksCheck](https://checkstyle.sourceforge.io/config_blocks.html#AvoidNestedBlocks) | .java, .jsp, .jspf, .tag, .tpl or .vm | Finds nested blocks (blocks that are used freely in the code). |
BNDCapabilityCheck | .bnd | Sorts and applies logic to fix line breaks to property values for `Provide-Capability` and `Require-Capability`. |
[BNDImportsCheck](checks/bnd_imports_check.markdown#bndimportscheck) | .bnd | Sorts class names and checks for use of wildcards in property values for `-conditionalpackage`, `-exportcontents` and `Export-Package`. |
BNDLineBreaksCheck | .bnd | Finds missing and unnecessary line breaks in `.bnd` files. |
BNDRunInstructionsOrderCheck | .bndrun | Sorts definition keys alphabetically. |
BNDStylingCheck | .bnd | Applies rules to enforce consisteny in code style. |
BNDWhitespaceCheck | .bnd | Finds missing and unnecessary whitespace in `.bnd` files. |
CSSCommentsCheck | .css or .scss | Validates comments in `.css` files. |
CSSImportsCheck | .css or .scss | Sorts and groups imports in `.css` files. |
CSSPropertiesOrderCheck | .css or .scss | Sorts properties in `.css` files. |
[ChainingCheck](checks/chaining_check.markdown#chainingcheck) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks that chaining is only applied on certain types and methods. |
CodeownersWhitespaceCheck | CODEOWNERS | Finds missing and unnecessary whitespace in `CODEOWNERS` files. |
ConfigDefinitionKeysCheck | .cfg or .config | Sorts definition keys in `.config` files. |
ConstructorMissingEmptyLineCheck | .java | Checks for line breaks when assiging variables in constructor. |
ContractionsCheck | .java, .jsp, .jspf, .tag, .tpl or .vm | Finds contractions in Strings (such as `can't` or `you're`). |
[CopyrightCheck](checks/copyright_check.markdown#copyrightcheck) | .java, .jsp, .jspf, .tag, .tpl or .vm | Validates `copyright` header. |
[DefaultComesLastCheck](https://checkstyle.sourceforge.io/config_coding.html#DefaultComesLast) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks that the `default` is after all the cases in a `switch` statement. |
DockerfileEmptyLinesCheck | Dockerfile | Finds missing and unnecessary empty lines. |
DockerfileInstructionCheck | Dockerfile | Performs styling rules on instructions in `Dockerfile` files. |
EmptyCollectionCheck | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks that there are no calls to `Collections.EMPTY_LIST`, `Collections.EMPTY_MAP` or `Collections.EMPTY_SET`. |
EnumConstantDividerCheck | .java | Find unnecessary empty lines between enum constants. |
EnumConstantOrderCheck | .java | Checks the order of enum constants. |
[ExceptionMessageCheck](checks/message_check.markdown#messagecheck) | .java, .jsp, .jspf, .tag, .tpl or .vm | Validates messages that are passed to exceptions. |
FTLEmptyLinesCheck | .ftl | Finds missing and unnecessary empty lines. |
[FTLIfStatementCheck](checks/if_statement_check.markdown#ifstatementcheck) | .ftl | Finds incorrect use of parentheses in statement. |
FTLImportsCheck | .ftl | Sorts and groups imports in `.ftl` files. |
FTLLiferayVariableOrderCheck | .ftl | Sorts assign statement of `liferay_*` variables. |
FTLStringRelationalOperatorCheck | .ftl | Finds cases of `==` or `!=` where `stringUtil.equals`, `validator.isNotNull` or `validator.isNull` can be used instead. |
FTLStylingCheck | .ftl | Applies rules to enforce consisteny in code style. |
FTLTagAttributesCheck | .ftl | Sorts and formats attributes values in tags. |
FTLTagCheck | .ftl | Finds cases where consecutive `#assign` can be combined. |
FTLWhitespaceCheck | .ftl | Finds missing and unnecessary whitespace in `.ftl` files. |
[GetterUtilCheck](checks/getter_util_check.markdown#getterutilcheck) | .java, .jsp, .jspf, .tag, .tpl or .vm | Finds cases where the default value is passed to `GetterUtil.get*` or `ParamUtil.get*`. |
GradleBlockOrderCheck | .gradle | Sorts logic in gradle build files. |
GradleBodyCheck | .gradle | Applies rules to enforce consisteny in the body of gradle build files. |
GradleImportsCheck | .gradle | Sorts and groups imports in `.gradle` files. |
GradleIndentationCheck | .gradle | Finds incorrect indentation in gradle build files. |
GradleStylingCheck | .gradle | Applies rules to enforce consisteny in code style. |
[GradleTaskCreationCheck](checks/gradle_task_creation_check.markdown#gradletaskcreationcheck) | .gradle | Checks that a task is declared on a separate line before the closure. |
HTMLEmptyLinesCheck | .html | Finds missing and unnecessary empty lines. |
HTMLWhitespaceCheck | .html | Finds missing and unnecessary whitespace in `.html` files. |
InstanceofOrderCheck | .java, .jsp, .jspf, .tag, .tpl or .vm | Check the order of `instanceof` calls. |
JSONStylingCheck | .json or .npmbridgerc | Applies rules to enforce consisteny in code style. |
[JSONUtilCheck](checks/json_util_check.markdown#jsonutilcheck) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks for utilization of class `JSONUtil`. |
JSPEmptyLinesCheck | .jsp, .jspf, .tag, .tpl or .vm | Finds missing and unnecessary empty lines. |
JSPExceptionOrderCheck | .jsp, .jspf, .tag, .tpl or .vm | Checks the order of exceptions in `.jsp` files. |
[JSPImportsCheck](checks/jsp_imports_check.markdown#jspimportscheck) | .jsp, .jspf, .tag, .tpl or .vm | Sorts and groups imports in `.jsp` files. |
JSPIndentationCheck | .jsp, .jspf, .tag, .tpl or .vm | Finds incorrect indentation in `.jsp` files. |
JSPJavaParserCheck | .jsp, .jspf, .tag, .tpl or .vm | Performs JavaParser on `.java` files. |
JSPLineBreakCheck | .jsp, .jspf, .tag, .tpl or .vm | Finds missing and unnecessary line breaks in `.jsp` lines. |
JSPStylingCheck | .jsp, .jspf, .tag, .tpl or .vm | Applies rules to enforce consisteny in code style. |
JSPWhitespaceCheck | .jsp, .jspf, .tag, .tpl or .vm | Finds missing and unnecessary whitespace in `.jsp` files. |
JSStylingCheck | .js or .jsx | Applies rules to enforce consisteny in code style. |
JSWhitespaceCheck | .js or .jsx | Finds missing and unnecessary whitespace in `.js` files. |
JavaAggregateTestRuleParameterOrderCheck | .java | Checks the order of parameters in `new AggregateTestRule` calls. |
JavaAnnotationDefaultAttributeCheck | .java | Finds cases where the default value is passed to annotations in package `*.bnd.annotations` or `*.bind.annotations`. |
JavaAssertEqualsCheck | .java | Validates `Assert.assertEquals` calls. |
[JavaConstructorParametersCheck](checks/java_constructor_parameters_check.markdown#javaconstructorparameterscheck) | .java | Checks that the order of variable assignments matches the order of the parameters in the constructor signature. |
JavaConstructorSuperCallCheck | .java | Finds unnecessary call to no-argument constructor of the superclass. |
JavaEmptyLinesCheck | .java | Finds missing and unnecessary empty lines. |
[JavaForLoopCheck](checks/java_for_loop_check.markdown#javaforloopcheck) | .java | Checks if a Enhanced For Loop can be used instead of a Simple For Loop. |
JavaIOExceptionCheck | .java | Validates use of `IOException`. |
JavaImportsCheck | .java | Sorts and groups imports in `.java` files. |
JavaInnerClassImportsCheck | .java | Finds cases where inner classes are imported. |
JavaLongLinesCheck | .java | Finds lines that are longer than the specified maximum line length. |
JavaReturnStatementCheck | .java | Finds unnecessary `else` statement (when `if` and `else` statement both end with `return` statement). |
JavaServiceObjectCheck | .java | Checks for correct use of `*.is*` instead of `*.get*` when calling methods generated by ServiceBuilder. |
[JavaSignatureParametersCheck](checks/java_signature_parameters_check.markdown#javasignatureparameterscheck) | .java | Checks the order of parameters. |
[JavaStaticImportsCheck](checks/java_static_imports_check.markdown#javastaticimportscheck) | .java | Checks that there are no static imports. |
JavaStylingCheck | .java | Applies rules to enforce consisteny in code style. |
[JavaSwitchCheck](checks/java_switch_check.markdown#javaswitchcheck) | .java | Checks that `if/else` statement is used instead of `switch` statement. |
JavaTermDividersCheck | .java | Finds missing or unnecessary empty lines between javaterms. |
JavaTermOrderCheck | .java | Checks the order of javaterms. |
JavaTermStylingCheck | .java | Applies rules to enforce consisteny in code style. |
[LambdaCheck](checks/lambda_check.markdown#lambdacheck) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks that `lambda` statements are as simple as possible. |
[ListUtilCheck](checks/list_util_check.markdown#listutilcheck) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks for utilization of class `ListUtil`. |
MarkdownFileExtensionCheck | .markdown or .md | Finds `markdown` files with `.md` extension (use `.markdown`). |
MarkdownStylingCheck | .markdown or .md | Applies rules to enforce consisteny in code style. |
MarkdownWhitespaceCheck | .markdown or .md | Finds missing and unnecessary whitespace in `.markdown` files. |
[MethodParamPadCheck](https://checkstyle.sourceforge.io/config_whitespace.html#MethodParamPad) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks the padding between the identifier of a method definition, constructor definition, method call, or constructor invocation; and the left parenthesis of the parameter list. |
[MissingEmptyLineCheck](checks/missing_empty_line_check.markdown#missingemptylinecheck) | .java | Checks for missing line breaks around variable declarations. |
[ModifierOrderCheck](https://checkstyle.sourceforge.io/config_modifier.html#ModifierOrder) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks that the order of modifiers conforms to the suggestions in the Java Language specification, § 8.1.1, 8.3.1, 8.4.3 and 9.4. |
[MultipleVariableDeclarationsCheck](https://checkstyle.sourceforge.io/config_coding.html#MultipleVariableDeclarations) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks that each variable declaration is in its own statement and on its own line. |
[NoLineWrapCheck](https://checkstyle.sourceforge.io/config_whitespace.html#NoLineWrap) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks that chosen statements are not line-wrapped. |
[NoWhitespaceAfterCheck](https://checkstyle.sourceforge.io/config_whitespace.html#NoWhitespaceAfter) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks that there is no whitespace after a token. |
[NoWhitespaceBeforeCheck](https://checkstyle.sourceforge.io/config_whitespace.html#NoWhitespaceBefore) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks that there is no whitespace before a token. |
[OneStatementPerLineCheck](https://checkstyle.sourceforge.io/config_coding.html#OneStatementPerLine) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks that there is only one statement per line. |
[OperatorWrapCheck](https://checkstyle.sourceforge.io/config_whitespace.html#OperatorWrap) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks the policy on how to wrap lines on operators. |
PoshiAnnotationsOrderCheck | .function, .macro or .testcase | Checks the order of annotations. |
PoshiCommandsOrderCheck | .function, .macro or .testcase | Checks the order of poshi commands. |
PoshiEmptyLinesCheck | .function, .macro or .testcase | Finds missing and unnecessary empty lines. |
PoshiIndentationCheck | .function, .macro or .testcase | Finds incorrect indentation in poshi files. |
PoshiParametersOrderCheck | .function, .macro or .testcase | Checks the order of parameters in `.function` and `.macro` files. |
PoshiStylingCheck | .function, .macro or .testcase | Applies rules to enforce consisteny in code style. |
PoshiWhitespaceCheck | .function, .macro or .testcase | Finds missing and unnecessary whitespace in poshi files. |
PropertiesEmptyLinesCheck | .prettierignore or .properties | Finds missing and unnecessary empty lines. |
PropertiesStylingCheck | .prettierignore or .properties | Applies rules to enforce consisteny in code style. |
PropertiesWhitespaceCheck | .prettierignore or .properties | Finds missing and unnecessary whitespace in `.properties` files. |
SQLEmptyLinesCheck | .sql | Finds missing and unnecessary empty lines. |
SQLStylingCheck | .sql | Applies rules to enforce consisteny in code style. |
SoyEmptyLinesCheck | .soy | Finds missing and unnecessary empty lines. |
[StringLiteralEqualityCheck](https://checkstyle.sourceforge.io/config_coding.html#StringLiteralEquality) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks that string literals are not used with == or !=. |
[UnnecessaryParenthesesCheck](https://checkstyle.sourceforge.io/config_coding.html#UnnecessaryParentheses) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks if unnecessary parentheses are used in a statement or expression. |
[WhitespaceAfterCheck](https://checkstyle.sourceforge.io/config_whitespace.html#WhitespaceAfter) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks that a token is followed by whitespace, with the exception that it does not check for whitespace after the semicolon of an empty for iterator. |
[WhitespaceAroundCheck](https://checkstyle.sourceforge.io/config_whitespace.html#WhitespaceAround) | .java, .jsp, .jspf, .tag, .tpl or .vm | Checks that a token is surrounded by whitespace. |
WhitespaceCheck | .cfg, .config, .cql, .css, .dtd, .expect, .gradle, .groovy, .scss, .sh, .soy, .sql, .tld, .ts, Dockerfile or packageinfo | Finds missing and unnecessary whitespace. |
XMLCustomSQLStylingCheck | .action, .function, .jrxml, .macro, .pom, .testcase, .toggle, .tpl, .wsdl, .xml or .xsd | Applies rules to enforce consisteny in code style for `.xml` files in directory `custom-sql`. |
XMLEmptyLinesCheck | .action, .function, .jrxml, .macro, .pom, .testcase, .toggle, .tpl, .wsdl, .xml or .xsd | Finds missing and unnecessary empty lines. |
XMLIndentationCheck | .action, .function, .jrxml, .macro, .pom, .testcase, .tld, .toggle, .tpl, .wsdl, .xml or .xsd | Finds incorrect indentation in `.xml` files. |
XMLStylingCheck | .action, .function, .jrxml, .macro, .pom, .testcase, .toggle, .tpl, .wsdl, .xml or .xsd | Applies rules to enforce consisteny in code style. |
XMLWhitespaceCheck | .action, .function, .jrxml, .macro, .pom, .testcase, .toggle, .tpl, .wsdl, .xml or .xsd | Finds missing and unnecessary whitespace in `.xml` files. |
YMLDefinitionOrderCheck | .yaml or .yml | Sorts definitions alphabetically in `.yml` files. |
YMLEmptyLinesCheck | .yaml or .yml | Finds missing and unnecessary empty lines. |
YMLLongLinesCheck | .yaml or .yml | Finds lines that are longer than the specified maximum line length. |
YMLStylingCheck | .yaml or .yml | Applies rules to enforce consisteny in code style. |
YMLWhitespaceCheck | .yaml or .yml | Finds missing and unnecessary whitespace in `.yml` files. |