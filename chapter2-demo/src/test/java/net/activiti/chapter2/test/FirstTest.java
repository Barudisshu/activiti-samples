package net.activiti.chapter2.test;

import net.activiti.chapter2.model.Person;
import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Test;

import java.util.Collection;

/**
 * @author Barudisshu
 */
public class FirstTest {

    @Test
    public void test() {

        // 创建一个KnowledgeBuilder
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // 添加规则资源到KnowledgeBuilder
        kbuilder.add(ResourceFactory.newClassPathResource("rule/first.drl", FirstTest.class), ResourceType.DRL);
        // 获取知识包集合
        Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();
        // 创建KnowledgeBase实例
        KnowledgeBase kbase = kbuilder.newKnowledgeBase();
        // 将知识包部署到KnowledgeBase中
        kbase.addKnowledgePackages(pkgs);
        // 使用KnowledgeBase创建StatefulKnowledgeSession
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        // 定义一个事实对象
        Person person = new Person();
        person.setName("Barudisshu");
        // 向StatefulKnowledgeSession中加入事实
        ksession.insert(person);
        // 匹配规则
        ksession.fireAllRules();
        // 关闭当前session的资源
        ksession.dispose();

    }
}
