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

/**
 * Class to hold the values of the parsing options.
 */
public final class ParserOptions
{
    public static final ParserOptions DEFAULT = new ParserOptions(false, false, false, null);

    private final boolean stopOnError;
    private final boolean warningsFatal;
    private final boolean suppressOutput;
    private final String xsdFilename;

    /**
     * Sets up the parsing options.
     *
     * @param stopOnError specifies whether the parsing should stop on error.
     * @param warningsFatal specifies whether the warnings should be handled as fatal errors.
     * @param suppressOutput specifies whether to suppress the output of errors and warnings.
     * @param xsdFilename the name of the schema file.
     */
    private ParserOptions(
        final boolean stopOnError, final boolean warningsFatal, final boolean suppressOutput, final String xsdFilename)
    {
        this.stopOnError = stopOnError;
        this.warningsFatal = warningsFatal;
        this.suppressOutput = suppressOutput;
        this.xsdFilename = xsdFilename;
    }

    /**
     * The value of the stopOnError parameter.
     *
     * @return true if we should stop on error.
     */
    public boolean stopOnError()
    {
        return stopOnError;
    }

    /**
     * The value of the warningsFatal parameter.
     *
     * @return true if warnings should be handled as errors.
     */
    public boolean warningsFatal()
    {
        return warningsFatal;
    }

    /**
     * The value of the suppressOutput parameter.
     *
     * @return true if we should suppress the output.
     */
    public boolean suppressOutput()
    {
        return suppressOutput;
    }

    /**
     * Returns the name of the schema file.
     *
     * @return the name of the schema file.
     */
    public String xsdFilename()
    {
        return xsdFilename;
    }

    /**
     * Creates a builder.
     *
     * @return a new builder instance.
     */
    public static Builder builder()
    {
        return new Builder();
    }

    public static class Builder
    {
        private boolean stopOnError;
        private boolean warningsFatal;
        private boolean suppressOutput;
        private String xsdFilename;

        /**
         * The value of the stopOnError parameter.
         *
         * @return true if we should stop on error.
         */
        public boolean stopOnError()
        {
            return stopOnError;
        }

        /**
         * Sets the value of the stopOnError parameter.
         *
         * @param stopOnError the new value for the parameter.
         * @return this instance
         */
        public Builder stopOnError(final boolean stopOnError)
        {
            this.stopOnError = stopOnError;
            return this;
        }

        /**
         * The value of the warningsFatal parameter.
         *
         * @return true if warnings should be handled as errors.
         */
        public boolean warningsFatal()
        {
            return warningsFatal;
        }

        /**
         * Sets the value for the warningsFatal parameter.
         *
         * @param warningsFatal the new value for the parameter.
         * @return this instance
         */
        public Builder warningsFatal(final boolean warningsFatal)
        {
            this.warningsFatal = warningsFatal;
            return this;
        }

        /**
         * The value of the suppressOutput parameter.
         *
         * @return true if we should suppress the output.
         */
        public boolean suppressOutput()
        {
            return suppressOutput;
        }

        /**
         * Sets the value for the suppressOutput parameter.
         *
         * @param suppressOutput the new value for the parameter.
         * @return this instance
         */
        public Builder suppressOutput(final boolean suppressOutput)
        {
            this.suppressOutput = suppressOutput;
            return this;
        }

        /**
         * Returns the name of the schema file.
         *
         * @return the name of the schema file.
         */
        public String xsdFilename()
        {
            return xsdFilename;
        }

        /**
         * Sets the schema filename.
         *
         * @param xsdFilename the name of the schema file.
         * @return this instance
         */
        public Builder xsdFilename(final String xsdFilename)
        {
            this.xsdFilename = xsdFilename;
            return this;
        }

        /**
         * Creates an instance of {@link ParserOptions} with all the values set.
         *
         * @return an instance of {@link ParserOptions} with all the values set.
         */
        public ParserOptions build()
        {
            return new ParserOptions(stopOnError, warningsFatal, suppressOutput, xsdFilename);
        }
    }
}
