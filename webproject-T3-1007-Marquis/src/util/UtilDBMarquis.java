/**
 */
package util;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import datamodel.Recipes;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDBMarquis {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<Recipes> listRecipes() {
      List<Recipes> resultList = new ArrayList<Recipes>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> recipes = session.createQuery("FROM Recipes").list();
         for (Iterator<?> iterator = recipes.iterator(); iterator.hasNext();) {
            Recipes recipe = (Recipes) iterator.next();
            resultList.add(recipe);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<Recipes> listRecipes(String keyword) {
      List<Recipes> resultList = new ArrayList<Recipes>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<?> recipes = session.createQuery("FROM Recipes").list();
         for (Iterator<?> iterator = recipes.iterator(); iterator.hasNext();) {
            Recipes recipe = (Recipes) iterator.next();
            if (recipe.getName().startsWith(keyword)) {
               resultList.add(recipe);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }
}
