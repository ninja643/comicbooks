//package rs.ac.ni.pmf.marko.comics.server.provider.impl;
//
//import java.util.Arrays;
//import java.util.List;
//
//import rs.ac.ni.pmf.marko.comics.server.datamodel.ComicBook;
//import rs.ac.ni.pmf.marko.comics.server.datamodel.Hero;
//import rs.ac.ni.pmf.marko.comics.server.datamodel.Publisher;
//import rs.ac.ni.pmf.marko.comics.server.provider.DataProvider;
//
//public class DataProviderStub implements DataProvider
//{
//	private static final Publisher[] PUBLISHERS = { new Publisher(1, "Zlatna Serija"), new Publisher(2, "LMS"),
//			new Publisher(3, "Veseli Četvrtak") };
//
//	private static final Hero[] HEROES = { new Hero(1, "Zagor"), new Hero(2, "Mr. No") };
//
//	private static final ComicBook[] BOOKS = {
//			new ComicBook(1, 13, "Nasilje u Darkvudu", null, PUBLISHERS[0], Arrays.asList(HEROES[0])) };
//
//	@Override
//	public List<ComicBook> getAllComicBooks()
//	{
//		return Arrays.asList(BOOKS);
//	}
//
//}
