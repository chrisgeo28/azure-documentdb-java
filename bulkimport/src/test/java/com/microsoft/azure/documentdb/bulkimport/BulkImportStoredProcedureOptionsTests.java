/**
 * The MIT License (MIT)
 * Copyright (c) 2017 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.microsoft.azure.documentdb.bulkimport;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BulkImportStoredProcedureOptionsTests {

    @Test
    public void serialization() throws IOException {
        BulkImportStoredProcedureOptions options = new BulkImportStoredProcedureOptions(true, true, "myCollectionId", true, false);

        ObjectMapper mapper = new ObjectMapper();

        String serializedValue = mapper.writeValueAsString(options);

        JsonNode root = mapper.readTree(serializedValue);
        assertThat(root.at("/disableAutomaticIdGeneration").asBoolean(), equalTo(true));
        assertThat(root.at("/softStopOnConflict").asBoolean(), equalTo(true));
        assertThat(root.at("/systemCollectionId").asText(), equalTo("myCollectionId"));
        assertThat(root.at("/enableUpsert").asBoolean(), equalTo(false));
    }
}
