# JPetStore - jdbcTemplate branch
CategoryDao를 Spring JDBC Template을 이용하여 구현함      

### master에 대한 변경 사항
* com.example.jpetstore.dao.jdbc 패키지에 CategoryDao의 구현 클래스들을 생성   
	- `PureJdbcCategoryDao` : Connection, Statement, ResultSet 등 JDBC API를 이용하여 구현    
	- `JdbcTemplateCategoryDao` : JdbcTemplate 클래스를 이용하여 구현   
	- `NamedParameterJdbcTemplateCategoryDao` : NamedParameterJdbcTemplate 클래스를 이용하여 구현   
	- `JdbcDaoSupportCategoryDao` : JdbcDaoSupport 클래스를 이용하여 구현      
      
       
* 서비스 클래스 `com.example.jpetstore.service.PetStoreImpl` 수정  
	- 5가지 CategoryDao 객체 주입 및 사용
<pre><code>
@Service
@Transactional
public class PetStoreImpl implements PetStoreFacade { 
   	@Autowired @Qualifier("mybatisCategoryDao")
	private CategoryDao categoryDao;  
	@Autowired @Qualifier("jdbcTemplateCategoryDao")
	private CategoryDao categoryDao1;  
	@Autowired @Qualifier("namedParamJdbcTemplateCategoryDao")
	private CategoryDao categoryDao2;  
	@Autowired @Qualifier("jdbcDaoSupportCategoryDao")
	private CategoryDao categoryDao3;  
	@Autowired @Qualifier("pureJdbcCategoryDao")
	private CategoryDao categoryDao4;	  
	public Category getCategory(String categoryId) {
		Category category = null;
		switch (categoryId) {
		case "FISH" :
			category = categoryDao.getCategory(categoryId);
			break;
		case "DOGS" :
			category = categoryDao1.getCategory(categoryId);
			break;
		case "REPTILES" :
			category = categoryDao2.getCategory(categoryId);
			break;
		case "CATS" :
			category = categoryDao3.getCategory(categoryId);
			break;
		case "BIRDS" :
			category = categoryDao4.getCategory(categoryId);
		}
		return category;
	}	
   	...
}
</pre></code> 
* `src/main/resources/dataAccessContext-jdbc.xml` 생성  
	- JdbcTemplate, NamedParameterJdbcTemplate 객체를 bean으로 설정  
	- DAO 구현 객체들은 component-scan을 통해 자동으로 bean으로 등록 (@Repository)  
	- JdbcDaoSupportCategoryDao bean에 대한 DataSource bean 주입 설정  
 
 	 
* `src/main/webapp/WEB-INF/web.xml` 수정  
	- ContextLoaderListener를 위한 contextConfigLocation 파라미터 설정에 `"classpath:dataAccessContext-jdbc.xml"` 추가
 