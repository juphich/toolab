package net.toolab.parser;

import java.io.IOException;
import java.io.InputStream;

import net.toolab.parser.exception.MessageParsingException;


public interface MessageParser<D> {

	D parse(InputStream source) throws IOException, MessageParsingException;
}
