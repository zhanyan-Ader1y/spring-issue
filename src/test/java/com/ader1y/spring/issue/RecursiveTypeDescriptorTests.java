package com.ader1y.spring.issue;

import org.junit.jupiter.api.Test;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RecursiveTypeDescriptorTests {

  /**
   * <pre>
   *  On this test case, stackoverflow start at {@link org.springframework.core.ResolvableType#equals}(ResolvableType.java:1023).
   *  The reason for infinite recursion is {@link ResolvableType.VariableResolver#getSource()} return cycle object likes:
   *  <blockquote><pre>
   *      class A extends HashMap<A, B>{}
   *  </pre></blockquote>
   *  or:
   *  <blockquote><pre>
   *      class C extends ArrayList<C>{}
   *  </pre></blockquote>
   * </pre>
   */
  @Test
  void test1() {
    TypeDescriptor typeDescriptor1 =
        TypeDescriptor.map(
            Map.class,
            TypeDescriptor.valueOf(String.class),
            TypeDescriptor.valueOf(RecursiveMap.class));
    TypeDescriptor typeDescriptor2 =
        TypeDescriptor.map(
            Map.class,
            TypeDescriptor.valueOf(String.class),
            TypeDescriptor.valueOf(RecursiveMap.class));
    ObjectUtils.nullSafeEquals(typeDescriptor1, typeDescriptor2);
  }

  /**
   * <pre>
   *  Why does this object implement the Map interface and throw diff stacktrace?
   *  I think this main reason same of test1, but some different in {@link ResolvableType#as(Class)}:
   *  <blockquote><pre>
   *  for (ResolvableType interfaceType : getInterfaces()) {
   * 	ResolvableType interfaceAsType = interfaceType.as(type);
   * 		if (interfaceAsType != NONE) {
   * 			return interfaceAsType;
   * 		}
   * 	}
   *  </pre></blockquote>
   *  focus on {@link ResolvableType#getInterfaces()}
   *  <blockquote><pre>
   *  	public ResolvableType[] getInterfaces() {
   * 		ResolvableType[] interfaces = this.interfaces;
   * 		if (interfaces == null) {
   * 			Type[] genericIfcs = resolved.getGenericInterfaces();
   * 			if (genericIfcs.length > 0) {
   * 				interfaces = new ResolvableType[genericIfcs.length];
   * 				for (int i = 0; i < genericIfcs.length; i++) {
   * 			        //  this forType function will use new ResolvableType(type, typeProvider, variableResolver)
   * 			        //  ResolvableType.calculateHashCode in this constructor
   * 					interfaces[i] = forType(genericIfcs[i], this);
   * 				}
   * 			}
   * 		......
   *  </pre></blockquote>
   *  </pre>
   *
   *
   */
  @Test
  void test2() {
    TypeDescriptor typeDescriptor1 =
        TypeDescriptor.map(
            Map.class,
            TypeDescriptor.valueOf(String.class),
            TypeDescriptor.valueOf(RecursiveMap2.class));
    TypeDescriptor typeDescriptor2 =
        TypeDescriptor.map(
            Map.class,
            TypeDescriptor.valueOf(String.class),
            TypeDescriptor.valueOf(RecursiveMap2.class));
    ObjectUtils.nullSafeEquals(typeDescriptor1, typeDescriptor2);
  }

  static class RecursiveMap extends HashMap<String, RecursiveMap> {}

  static class RecursiveMap2 extends HashMap<RecursiveMap2, String>
      implements Map<RecursiveMap2, String> {}

  //  throw STOF
  static class RecursiveList extends ArrayList<RecursiveList>{

  }
}
