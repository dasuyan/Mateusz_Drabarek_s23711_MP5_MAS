import association.Caretaker;
import association.Cat;
import inheritance.Car;
import inheritance.Motorcycle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import simple_class.Toy;

import java.time.LocalDate;
import java.util.List;

public class Main {

	public static void main(String[] args) {
	//	testClass();
	//	testAssociation();
	//	testInheritance();
	}

	public static void testClass() {
		StandardServiceRegistry registry = null;
		SessionFactory sessionFactory = null;

		try {
			registry = new StandardServiceRegistryBuilder()
					.configure() // configures settings from hibernate.cfg.xml
					.build();
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

			System.out.println("Created toys:");
			var toy1 = new Toy("Ben 10: Omnitrix", LocalDate.of(2006, 10,26));
			var toy2 = new Toy("My Little Pony Treehouse", LocalDate.of(2009, 8,8));

			System.out.println(toy1);
			System.out.println(toy2);

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(toy1);
			session.save(toy2);
			session.getTransaction().commit();
			session.close();

			System.out.println("\nToys from the db:");

			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Toy> toysFromDb = session.createQuery("from Toy").list();
			for (var toy : toysFromDb) {
				System.out.println(toy);
			}
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy( registry );
		} finally {
			if (sessionFactory != null) {
				sessionFactory.close();
				sessionFactory = null;
			}
		}
	}

	public static void testAssociation() {
		StandardServiceRegistry registry = null;
		SessionFactory sessionFactory = null;

		try {
			registry = new StandardServiceRegistryBuilder()
					.configure() // configures settings from hibernate.cfg.xml
					.build();
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

			System.out.println("Created caretakers:");
			var caretaker1 = new Caretaker("Mateusz", "Drabarek");
			var caretaker2 = new Caretaker("Artur", "Konopka");

			System.out.println(caretaker1);
			System.out.println(caretaker2);

			System.out.println("Created cats:");
			var cat1 = new Cat("Puszek", LocalDate.of(2020, 1, 29), "male", "ginger");
			var cat2 = new Cat("Sonia", LocalDate.of(2011, 7, 30), "female", "tricolore");
			var cat3 = new Cat("Tofik", LocalDate.of(1900, 12, 12), "male", "sphinx");

			System.out.println(cat1);
			System.out.println(cat2);
			System.out.println(cat3);

			caretaker1.addCat(cat1);
			caretaker1.addCat(cat2);
			caretaker2.addCat(cat3);

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(caretaker1);
			session.save(caretaker2);
			session.save(cat1);
			session.save(cat2);
			session.save(cat3);
			session.getTransaction().commit();
			session.close();

			System.out.println("\nCaretakers and cats from the db:");

			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Caretaker> caretakersFromDb = session.createQuery( "from Caretaker" ).list();
			for (var caretaker : caretakersFromDb) {
				System.out.println(caretaker);
			}
			List<Cat> catsFromDb = session.createQuery( "from Cat" ).list();
			for (var cat : catsFromDb) {
				System.out.println(cat);
			}
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy( registry );
		} finally {
			if (sessionFactory != null) {
				sessionFactory.close();
				sessionFactory = null;
			}
		}
	}

	public static void testInheritance() {
		StandardServiceRegistry registry = null;
		SessionFactory sessionFactory = null;

		try {
			registry = new StandardServiceRegistryBuilder()
					.configure() // configures settings from hibernate.cfg.xml
					.build();
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();


			var car1 = new Car("Audi", "Q7", 7);
			var motorcycle1 = new Motorcycle("Honda", "Akura", false);

			System.out.println("Created cars:");
			System.out.println(car1);

			System.out.println("Created motorcycles:");
			System.out.println(motorcycle1);

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(car1);
			session.save(motorcycle1);
			session.getTransaction().commit();
			session.close();

			System.out.println("\nCars and motorcycles from the db:");

			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Car> carsFromDb = session.createQuery( "from Car" ).list();
			for (var car : carsFromDb) {
				System.out.println(car);
			}
			List<Motorcycle> motorcyclesFromDb = session.createQuery( "from Motorcycle " ).list();
			for (var motorcycle : motorcyclesFromDb) {
				System.out.println(motorcycle);
			}
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy( registry );
		} finally {
			if (sessionFactory != null) {
				sessionFactory.close();
				sessionFactory = null;
			}
		}
	}
}
