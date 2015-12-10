package ralen.base;
//
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;
//
//import org.reflections.Reflections;
//import org.reflections.scanners.FieldAnnotationsScanner;
//import org.reflections.scanners.SubTypesScanner;
//import org.reflections.scanners.TypeAnnotationsScanner;
//import org.reflections.util.ClasspathHelper;
//import org.reflections.util.ConfigurationBuilder;
//
//
//public class ApplicationContextImp implements ApplicationContext{
//
//	private final static Logger logger = LoggerFactory.getLogger(ApplicationContextImp.class);
//	
//	private final Reflections reflections;
//	private final Map<String, Object> beansMap;
//	private final Map<Class<?>, Object> classMap;
//
//	public ApplicationContextImp(String basePackage) {
//		beansMap = new HashMap<String, Object>();
//		classMap = new HashMap<Class<?>, Object>();
//		reflections = new Reflections(new ConfigurationBuilder()
//										.setUrls( ClasspathHelper.forPackage(basePackage) )
//										.setScanners( new FieldAnnotationsScanner(),
//													  new TypeAnnotationsScanner(),
//													  new SubTypesScanner()));
//		
//		final Set< Class<?> > beans = reflections.getTypesAnnotatedWith(Bean.class);
//		processTypeBeans(beans);
//		
//		final Set< Class<?> > configurations = reflections.getTypesAnnotatedWith(Configuration.class);
//		processConfigurationBeans(configurations);
//
//		mapInjectedFields();
//	}
//	
//	public void sample(){
//		for(Entry<String, Object> entry: beansMap.entrySet()){
//			System.out.println(entry.getKey() + " " + entry.getValue());
//		}
//	}
//
//	public Collection<Object> getBeans() {
//		return beansMap.values();
//	}
//	
//	@SuppressWarnings("unchecked")
//	public <T> T getBean(String name) {
//		return (T) convertInstanceOfObject(beansMap.get(name), beansMap.get(name).getClass());
//	}
//	
//	private boolean processTypeBeans( Set< Class<?> > beans){
//		for ( Class<?> bean : beans ) {
//			logger.info("Bean " + bean.getName() + " initialization");
//			try {
//				Bean ann = bean.getAnnotation(Bean.class);
//				String name = ann.value();
//				if(name.length() == 0){
//					name = bean.getSimpleName().toLowerCase();
//				}
//				instantiateClass(bean, name);
//			} catch (Exception e) {
//				logger.error("Error creating bean " + bean.getSimpleName());
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	private boolean processConfigurationBeans( Set< Class<?> > configurations ){
//		for( Class<?> config : configurations){
//			Object obj = instantiateClass(config, config.getSimpleName());
//			for( Method method : config.getDeclaredMethods()){
//				if(method.getAnnotation(Bean.class) == null){
//					continue;
//				}
//				try {
//					Object[] param = {};
//					System.out.println(method.getName());
//					method.setAccessible(true);
//					Object ret = method.invoke(obj, param);
//					putObject(method.getReturnType(), ret, method.getName());
//				} catch (Exception e) {
//					return false;
//				}
//			}
//		}
//		return true;
//	}
//	
//	private boolean mapInjectedFields(){
//		for(Entry<Class<?>, Object> entry: classMap.entrySet()){
//			try {
//				assignFields(entry.getKey(), entry.getValue());
//			} catch (Exception e) {
//				return false;
//			}
//		}
//		return true;
//	}
//	
//	private static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
//	    try {
//	        return clazz.cast(o);
//	    } catch(ClassCastException e) {
//	        return null;
//	    }
//	}
//	
//	@SuppressWarnings("unchecked")
//	public <T> T getBean(Class<?> clazz){
//		return (T) convertInstanceOfObject(classMap.get(clazz), clazz);
//	}
//	
//	private void putObject(Class<?> clazz, Object object, String name){
//		classMap.put(clazz, object);
//		beansMap.put(name, object);
//	}
//	
//	private Object instantiateClass(Class<?> bean, String beanName) {
//		try{
//			Object beanObj = bean.newInstance();
//			beansMap.put(beanName.toLowerCase(), beanObj);
//			classMap.put(bean, beanObj);
//			logger.info("Bean " + bean.getName() + " instantiated");
//			return beanObj;
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	private void assignFields(Class<?> beans, Object obj) throws IllegalArgumentException, IllegalAccessException{
//		for(Field field: beans.getDeclaredFields()){
//			field.setAccessible(true);
//			Inject inject = field.getAnnotation(Inject.class);
//			System.out.println("Process field : " + field.getName());
//			if(inject != null){
//				String fieldName = inject.value();
//				Object fieldObj = null;
//				if(fieldName.length() == 0){
//					fieldObj = beansMap.get(field.getType().getSimpleName().toLowerCase());
//					if(fieldObj == null){
//						System.out.println("Cannot inject bean " + field.getType());
//					}
//				}else{
//					fieldObj = beansMap.get(fieldName.toLowerCase());
//					if(fieldObj == null){
//						System.out.println("Cannot inject bean " + field.getType());
//					}
//				}
//				field.set(obj, fieldObj);
//			}
//		}
//	}
//}
