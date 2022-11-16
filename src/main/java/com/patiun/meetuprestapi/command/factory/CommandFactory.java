package com.patiun.meetuprestapi.command.factory;

import com.patiun.meetuprestapi.command.Command;
import com.patiun.meetuprestapi.dao.helper.DaoHelperFactory;

public class CommandFactory {

    public Command createCommand(String command) {
        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        switch (command) {
            case CommandLineConstants.SIGN_IN_PAGE:
                return new ForwardCommand(PagePathConstants.SIGN_IN);
            case CommandLineConstants.SIGN_IN:
                return new SignInCommand(new UserServiceImpl(daoHelperFactory, new UserValidator(), new PasswordValidator()));
            case CommandLineConstants.SIGN_UP_PAGE:
                return new ForwardCommand(PagePathConstants.SIGN_UP);
            case CommandLineConstants.SIGN_UP:
                return new SignUpCommand(new UserServiceImpl(daoHelperFactory, new UserValidator(), new PasswordValidator()));
            case CommandLineConstants.LANGUAGE_CHANGE:
                return new LanguageChangeCommand();
            case CommandLineConstants.SIGN_OUT:
                return new SignOutCommand();
            case CommandLineConstants.MAIN_PAGE:
                return new MainPageCommand();
            case CommandLineConstants.BOOKS_PAGE:
                return new BooksPageCommand(new BookServiceImpl(daoHelperFactory, assemblerFactory, new BookValidator(), new AuthorsLineParser()), new Paginator<>());
            case CommandLineConstants.SEARCH_BOOKS:
                return new SearchBooksPageCommand(new BookServiceImpl(daoHelperFactory, assemblerFactory, new BookValidator(), new AuthorsLineParser()), new Paginator<>());
            case CommandLineConstants.VIEW_BOOK_PAGE:
                return new ViewBookPageCommand(new BookServiceImpl(daoHelperFactory, assemblerFactory, new BookValidator(), new AuthorsLineParser()));
            case CommandLineConstants.EDIT_BOOK_PAGE:
                return new EditBookPageCommand(new BookServiceImpl(daoHelperFactory, assemblerFactory, new BookValidator(), new AuthorsLineParser()));
            case CommandLineConstants.ADD_BOOK:
                return new AddABookCommand(new BookServiceImpl(daoHelperFactory, assemblerFactory, new BookValidator(), new AuthorsLineParser()));
            case CommandLineConstants.EDIT_BOOK:
                return new EditBookCommand(new BookServiceImpl(daoHelperFactory, assemblerFactory, new BookValidator(), new AuthorsLineParser()));
            case CommandLineConstants.DELETE_BOOK:
                return new DeleteBookCommand(new BookServiceImpl(daoHelperFactory, assemblerFactory, new BookValidator(), new AuthorsLineParser()));
            case CommandLineConstants.ADD_A_BOOK_PAGE:
                return new ForwardCommand(PagePathConstants.ADD_A_BOOK);
            case CommandLineConstants.USERS_PAGE:
                return new UsersPageCommand(new UserServiceImpl(daoHelperFactory, new UserValidator(), new PasswordValidator()), new Paginator<>());
            case CommandLineConstants.SEARCH_USERS:
                return new SearchUsersPageCommand(new UserServiceImpl(daoHelperFactory, new UserValidator(), new PasswordValidator()), new Paginator<>());
            case CommandLineConstants.VIEW_USER_PAGE:
                return new ViewUserPageCommand(new UserServiceImpl(daoHelperFactory, new UserValidator(), new PasswordValidator()));
            case CommandLineConstants.EDIT_USER_PAGE:
                return new EditUserPageCommand(new UserServiceImpl(daoHelperFactory, new UserValidator(), new PasswordValidator()));
            case CommandLineConstants.EDIT_USER:
                return new EditUserCommand(new UserServiceImpl(daoHelperFactory, new UserValidator(), new PasswordValidator()));
            case CommandLineConstants.BLOCK_USER:
                return new SetUserBlockedStatusCommand(new UserServiceImpl(daoHelperFactory, new UserValidator(), new PasswordValidator()), true);
            case CommandLineConstants.UNBLOCK_USER:
                return new SetUserBlockedStatusCommand(new UserServiceImpl(daoHelperFactory, new UserValidator(), new PasswordValidator()), false);
            case CommandLineConstants.ORDERS_PAGE:
                return new OrdersPageCommand(new BookOrderServiceImpl(daoHelperFactory, assemblerFactory, new BookOrderValidator()), new Paginator<>());
            case CommandLineConstants.SEARCH_ORDERS:
                return new SearchOrdersPageCommand(new BookOrderServiceImpl(daoHelperFactory, assemblerFactory, new BookOrderValidator()), new Paginator<>());
            case CommandLineConstants.VIEW_ORDER:
                return new ViewOrderPageCommand(new BookOrderServiceImpl(daoHelperFactory, assemblerFactory, new BookOrderValidator()));
            case CommandLineConstants.PLACE_ORDER:
                return new SaveOrderCommand(new BookOrderServiceImpl(daoHelperFactory, assemblerFactory, new BookOrderValidator()));
            case CommandLineConstants.ADVANCE_ORDER:
                return new OrderStateAdvancementCommand(new BookOrderServiceImpl(daoHelperFactory, assemblerFactory, new BookOrderValidator()));
            default:
                throw new IllegalArgumentException("Unknown command = " + command);
        }
    }
}
