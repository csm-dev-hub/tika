/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tika.parser.html.charsetdetector;


import org.apache.tika.parser.html.charsetdetector.charsets.ReplacementCharset;
import org.apache.tika.parser.html.charsetdetector.charsets.XUserDefinedCharset;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Singleton class that associates standard charset names to java charset implementations
 * https://encoding.spec.whatwg.org/#ref-for-iso-8859-8-i
 */
final class CharsetAliases {

    private static final Map<String, Charset> charsetsByLabel = new HashMap<>();

    private CharsetAliases() {
    }

    /**
     * @param label a charset name
     * @return the corresponding java charset, if there is one. Otherwise, null
     */
    static Charset getCharsetByLabel(String label) {
        if (label == null) return null;
        synchronized (charsetsByLabel) {
            // Lazy initialization
            if (charsetsByLabel.isEmpty()) addAll();
        }
        label = label.trim().toLowerCase(Locale.US);
        return charsetsByLabel.get(label);
    }

    private static void addAll() {
        addCharset(charset("Big5"), "big5", "big5-hkscs", "cn-big5", "csbig5", "x-x-big5");
        addCharset(charset("EUC-JP"), "cseucpkdfmtjapanese", "euc-jp", "x-euc-jp");
        addCharset(charset("EUC-KR"), "cseuckr", "csksc56011987", "euc-kr", "iso-ir-149", "korean",
                "ks_c_5601-1987", "ks_c_5601-1989", "ksc5601", "ksc_5601", "windows-949");
        addCharset(charset("GBK"), "chinese", "csgb2312", "csiso58gb231280", "gb2312", "gb_2312",
                "gb_2312-80", "gbk", "iso-ir-58", "x-gbk");
        addCharset(charset("IBM866"), "866", "cp866", "csibm866", "ibm866");
        addCharset(charset("ISO-2022-JP"), "csiso2022jp", "iso-2022-jp");
        addCharset(charset("ISO-8859-10", "ISO-8859-4"), "csisolatin6", "iso-8859-10", "iso-ir-157",
                "iso8859-10", "iso885910", "l6", "latin6");
        addCharset(charset("ISO-8859-13"), "iso-8859-13", "iso8859-13", "iso885913");
        addCharset(charset("ISO-8859-14", "ISO-8859-1"), "iso-8859-14", "iso8859-14", "iso885914");
        addCharset(charset("ISO-8859-15"), "csisolatin9", "iso-8859-15", "iso8859-15", "iso885915",
                "iso_8859-15", "l9");
        addCharset(charset("ISO-8859-16", "ISO-8859-1"), "iso-8859-16");
        addCharset(charset("ISO-8859-2"), "csisolatin2", "iso-8859-2", "iso-ir-101", "iso8859-2",
                "iso88592", "iso_8859-2", "iso_8859-2:1987", "l2", "latin2");
        addCharset(charset("ISO-8859-3"), "csisolatin3", "iso-8859-3", "iso-ir-109", "iso8859-3",
                "iso88593", "iso_8859-3", "iso_8859-3:1988", "l3", "latin3");
        addCharset(charset("ISO-8859-4"), "csisolatin4", "iso-8859-4", "iso-ir-110", "iso8859-4",
                "iso88594", "iso_8859-4", "iso_8859-4:1988", "l4", "latin4");
        addCharset(charset("ISO-8859-5"), "csisolatincyrillic", "cyrillic", "iso-8859-5", "iso-ir-144",
                "iso8859-5", "iso88595", "iso_8859-5", "iso_8859-5:1988");
        addCharset(charset("ISO-8859-6"), "arabic", "asmo-708", "csiso88596e", "csiso88596i",
                "csisolatinarabic", "ecma-114", "iso-8859-6", "iso-8859-6-e", "iso-8859-6-i", "iso-ir-127",
                "iso8859-6", "iso88596", "iso_8859-6", "iso_8859-6:1987");
        addCharset(charset("ISO-8859-7"), "csisolatingreek", "ecma-118", "elot_928", "greek", "greek8",
                "iso-8859-7", "iso-ir-126", "iso8859-7", "iso88597", "iso_8859-7", "iso_8859-7:1987", "sun_eu_greek");
        // ISO-8859-8 actually should have an influence on the layout direction
        // (text should be decoded in the visual order). However, this is not implemented in tika.
        addCharset(charset("ISO-8859-8"), "csiso88598e", "csisolatinhebrew", "hebrew", "iso-8859-8",
                "iso-8859-8-e", "iso-ir-138", "iso8859-8", "iso88598", "iso_8859-8", "iso_8859-8:1988", "visual");
        addCharset(charset("ISO-8859-8-I", "ISO-8859-8"), "csiso88598i", "iso-8859-8-i", "logical");
        addCharset(charset("KOI8-R"), "cskoi8r", "koi", "koi8", "koi8-r", "koi8_r");
        addCharset(charset("KOI8-U"), "koi8-ru", "koi8-u");
        addCharset(charset("Shift_JIS"), "csshiftjis", "ms932", "ms_kanji", "shift-jis", "shift_jis",
                "sjis", "windows-31j", "x-sjis");
        addCharset(charset("UTF-16BE"), "utf-16be");
        addCharset(charset("UTF-16LE"), "utf-16", "utf-16le");
        addCharset(charset("UTF-8"), "unicode-1-1-utf-8", "utf-8", "utf8");
        addCharset(charset("gb18030"), "gb18030");
        addCharset(charset("windows-1250"), "cp1250", "windows-1250", "x-cp1250");
        addCharset(charset("windows-1251"), "cp1251", "windows-1251", "x-cp1251");
        addCharset(charset("windows-1252"), "ansi_x3.4-1968", "ascii", "cp1252", "cp819", "csisolatin1",
                "ibm819", "l1", "latin1", "us-ascii", "windows-1252", "x-cp1252");
        addCharset(charset("ISO-8859-1"), "iso-8859-1", "iso-ir-100", "iso8859-1", "iso88591",
                "iso_8859-1", "iso_8859-1:1987");
        addCharset(charset("windows-1253"), "cp1253", "windows-1253", "x-cp1253");
        addCharset(charset("windows-1254"), "cp1254", "csisolatin5", "iso-8859-9", "iso-ir-148",
                "iso8859-9", "iso88599", "iso_8859-9", "iso_8859-9:1989", "l5", "latin5", "windows-1254", "x-cp1254");
        addCharset(charset("windows-1255"), "cp1255", "windows-1255", "x-cp1255");
        addCharset(charset("windows-1256"), "cp1256", "windows-1256", "x-cp1256");
        addCharset(charset("windows-1257"), "cp1257", "windows-1257", "x-cp1257");
        addCharset(charset("windows-1258"), "cp1258", "windows-1258", "x-cp1258");
        addCharset(charset("windows-874"), "dos-874", "iso-8859-11", "iso8859-11", "iso885911",
                "tis-620", "windows-874");
        addCharset(charset("x-MacCyrillic"), "x-mac-cyrillic", "x-mac-ukrainian");
        addCharset(charset("x-MacRoman"), "csmacintosh", "mac", "macintosh", "x-mac-roman");
        // The "replacement" charset is a dummy charset. It is present to mitigate wrong-charset attacks
        addCharset(new ReplacementCharset(), "csiso2022kr", "hz-gb-2312", "iso-2022-cn", "iso-2022-cn-ext",
                "iso-2022-kr", "replacement");
        // The x-user-defined charset is not present in java
        addCharset(new XUserDefinedCharset(), "x-user-defined");
    }

    /**
     * @param names jvm charset names
     * @return the first of the given charsets that exists in the current JVM,
     * or ISO_8859_1 if none exists
     */
    private static Charset charset(String... names) {
        for (String name : names) {
            try {
                return Charset.forName(name);
            } catch (IllegalCharsetNameException | UnsupportedCharsetException e) {/* pass */}
        }
        // The only single-byte charset extended charset that must be present on every Java platform
        return StandardCharsets.ISO_8859_1;
    }

    /**
     * @param charset name of the charset in the JVM
     * @param names   standard W3C charset names
     */
    private static void addCharset(Charset charset, String... names) {
        for (String name : names) {
            charsetsByLabel.put(name, charset);
        }
    }
}
