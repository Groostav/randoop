package org.apache.commons.jelly.tags.velocity;

/*
 * Copyright 2001,2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.commons.jelly.TagLibrary;

/**
 * Describes the Taglib. This class could be generated by XDoclet
 *
 * @author <a href="mailto:pete-apache-dev@kazmier.com">Pete Kazmier</a>
 * @version $Id: VelocityTagLibrary.java,v 1.3 2004/09/09 12:23:16 dion Exp $
 */
public class VelocityTagLibrary extends TagLibrary
{
    public VelocityTagLibrary()
    {
        registerTag( "merge", MergeTag.class );
    }
}