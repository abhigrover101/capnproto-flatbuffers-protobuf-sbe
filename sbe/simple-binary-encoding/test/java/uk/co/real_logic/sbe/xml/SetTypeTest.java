/* -*- mode: java; c-basic-offset: 4; tab-width: 4; indent-tabs-mode: nil -*- */
/*
 * Copyright 2013 Real Logic Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.real_logic.sbe.xml;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import uk.co.real_logic.sbe.PrimitiveType;
import uk.co.real_logic.sbe.PrimitiveValue;
import uk.co.real_logic.sbe.TestUtil;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static uk.co.real_logic.sbe.xml.XmlSchemaParser.parse;

public class SetTypeTest
{
    @Test
    public void shouldHandleBinarySetType()
        throws Exception
    {
        final String testXmlString =
            "<types>" +
            "<set name=\"biOp\" encodingType=\"uint8\">" +
            "    <choice name=\"Bit0\" description=\"Bit 0\">0</choice>" +
            "    <choice name=\"Bit1\" description=\"Bit 1\">1</choice>" +
            "</set>" +
            "</types>";

        final Map<String, Type> map = parseTestXmlWithMap("/types/set", testXmlString);
        final SetType e = (SetType)map.get("biOp");

        assertThat(e.name(), is("biOp"));
        assertThat(e.encodingType(), is(PrimitiveType.UINT8));
        assertThat(e.choices().size(), is(2));
        assertThat(e.getChoice("Bit1").primitiveValue(), is(PrimitiveValue.parse("1", PrimitiveType.UINT8)));
        assertThat(e.getChoice("Bit0").primitiveValue(), is(PrimitiveValue.parse("0", PrimitiveType.UINT8)));
    }

    @Test
    public void shouldHandleSetTypeList()
        throws Exception
    {
        final String testXmlString =
            "<types>" +
            "<set name=\"listed\" encodingType=\"uint8\">" +
            "    <choice name=\"Bit0\">0</choice>" +
            "    <choice name=\"Bit1\">1</choice>" +
            "    <choice name=\"Bit2\">2</choice>" +
            "    <choice name=\"Bit3\">3</choice>" +
            "</set>" +
            "</types>";

        final Map<String, Type> map = parseTestXmlWithMap("/types/set", testXmlString);
        final SetType e = (SetType)map.get("listed");

        assertThat(e.encodingType(), is(PrimitiveType.UINT8));

        int foundBit0 = 0, foundBit1 = 0, foundBit2 = 0, foundBit3 = 0, count = 0;
        for (final SetType.Choice choice : e.choices())
        {
            if (choice.name().equals("Bit0"))
            {
                foundBit0++;
            }
            else if (choice.name().equals("Bit1"))
            {
                foundBit1++;
            }
            else if (choice.name().equals("Bit2"))
            {
                foundBit2++;
            }
            else if (choice.name().equals("Bit3"))
            {
                foundBit3++;
            }
            count++;
        }

        assertThat(count, is(4));
        assertThat(foundBit0, is(1));
        assertThat(foundBit1, is(1));
        assertThat(foundBit2, is(1));
        assertThat(foundBit3, is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIllegalEncodingTypeSpecified()
        throws Exception
    {
        final String testXmlString =
            "<types>" +
            "<set name=\"biOp\" encodingType=\"char\">" +
            "    <choice name=\"Bit0\">0</choice>" +
            "    <choice name=\"Bit1\">1</choice>" +
            "</set>" +
            "</types>";

        parseTestXmlWithMap("/types/set", testXmlString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDuplicateValueSpecified()
        throws Exception
    {
        final String testXmlString =
            "<types>" +
            "<set name=\"biOp\" encodingType=\"uint8\">" +
            "    <choice name=\"Bit0\">0</choice>" +
            "    <choice name=\"AnotherBit0\">0</choice>" +
            "</set>" +
            "</types>";

        parseTestXmlWithMap("/types/set", testXmlString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenDuplicateNameSpecified()
        throws Exception
    {
        final String testXmlString =
            "<types>" +
            "<set name=\"biOp\" encodingType=\"uint8\">" +
            "    <choice name=\"Bit0\">0</choice>" +
            "    <choice name=\"Bit0\">1</choice>" +
            "</set>" +
            "</types>";

        parseTestXmlWithMap("/types/set", testXmlString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenValueOutOfBoundsSpecified()
        throws Exception
    {
        final String testXmlString =
            "<types>" +
            "<set name=\"biOp\" encodingType=\"uint8\">" +
            "    <choice name=\"Bit0\">0</choice>" +
            "    <choice name=\"Bit100\">100</choice>" +
            "</set>" +
            "</types>";

        parseTestXmlWithMap("/types/set", testXmlString);
    }

     @Test
     public void shouldHandleEncodingTypesWithNamedTypes()
         throws Exception
     {
         final MessageSchema schema = parse(TestUtil.getLocalResource("encoding-types-schema.xml"), ParserOptions.DEFAULT);
         final List<Field> fields = schema.getMessage(1).fields();

         assertNotNull(fields);

         SetType type = (SetType)fields.get(3).type();

         assertThat(type.encodingType(), is(PrimitiveType.UINT8));

         type = (SetType)fields.get(4).type();

         assertThat(type.encodingType(), is(PrimitiveType.UINT16));

         type = (SetType)fields.get(5).type();

         assertThat(type.encodingType(), is(PrimitiveType.UINT32));

         type = (SetType)fields.get(6).type();

         assertThat(type.encodingType(), is(PrimitiveType.UINT64));
     }

    private static Map<String, Type> parseTestXmlWithMap(final String xPathExpr, final String xml)
        throws ParserConfigurationException, XPathExpressionException, IOException, SAXException
    {
        final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(
            new ByteArrayInputStream(xml.getBytes()));
        final XPath xPath = XPathFactory.newInstance().newXPath();
        final NodeList list = (NodeList)xPath.compile(xPathExpr).evaluate(document, XPathConstants.NODESET);
        final Map<String, Type> map = new HashMap<>();

        final ParserOptions options = ParserOptions.builder().stopOnError(true).suppressOutput(true).warningsFatal(true).build();
        document.setUserData(XmlSchemaParser.ERROR_HANDLER_KEY, new ErrorHandler(options), null);

        for (int i = 0, size = list.getLength(); i < size; i++)
        {
            final Type t = new SetType(list.item(i));
            map.put(t.name(), t);
        }

        return map;
    }
}
