package j2html.tags;

import j2html.attributes.Attribute;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public abstract class Tag<T extends Tag<T>> extends DomContent {
    protected String tagName;
    private ArrayList<Attribute> attributes;

    protected Tag(String tagName) {
        this.tagName = tagName;
        this.attributes = new ArrayList();
    }

    public String getTagName() {
        return this.tagName;
    }

    protected boolean hasTagName() {
        return this.tagName != null && !this.tagName.isEmpty();
    }

    String renderOpenTag() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        this.renderOpenTag(stringBuilder, (Object)null);
        return stringBuilder.toString();
    }

    String renderCloseTag() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        this.renderCloseTag(stringBuilder);
        return stringBuilder.toString();
    }

    void renderOpenTag(Appendable writer, Object model) throws IOException {
        if (this.hasTagName()) {
            writer.append("<").append(this.tagName);
            Iterator var3 = this.attributes.iterator();

            while(var3.hasNext()) {
                Attribute attribute = (Attribute)var3.next();
                attribute.renderModel(writer, model);
            }

            writer.append(">");
        }
    }

    void renderCloseTag(Appendable writer) throws IOException {
        if (this.hasTagName()) {
            writer.append("</");
            writer.append(this.tagName);
            writer.append(">");
        }
    }

    ArrayList<Attribute> getAttributes() {
        return this.attributes;
    }

    boolean setAttribute(String name, String value) {
        if (value == null) {
            return this.attributes.add(new Attribute(name));
        } else {
            Iterator var3 = this.attributes.iterator();

            Attribute attribute;
            do {
                if (!var3.hasNext()) {
                    return this.attributes.add(new Attribute(name, value));
                }

                attribute = (Attribute)var3.next();
            } while(!attribute.getName().equals(name));

            attribute.setValue(value);
            return true;
        }
    }

    public String attrValue(String param) {
        return getAttributes().stream().filter( t -> t.getName().equals(param)).map(Attribute::getName).findFirst().orElse(null);
    }

    public boolean hasAttr(String param) {
        return getAttributes().stream().filter( t -> t.getName().equals(param)).map(Attribute::getValue).map(Objects::nonNull).findFirst().orElse(false);
    }

    public T attr(String attribute, Object value) {
        this.setAttribute(attribute, value == null ? null : String.valueOf(value));
        return this;
    }

    public T attr(Attribute attribute) {
        Iterator<Attribute> iterator = this.attributes.iterator();
        String name = attribute.getName();
        if (name != null) {
            while(iterator.hasNext()) {
                Attribute existingAttribute = (Attribute)iterator.next();
                if (existingAttribute.getName().equals(name)) {
                    iterator.remove();
                }
            }
        }

        this.attributes.add(attribute);
        return this;
    }

    public T attr(String attribute) {
        return this.attr(attribute, (Object)null);
    }

    public T condAttr(boolean condition, String attribute, String value) {
        return condition ? this.attr(attribute, value) : this;
    }

    public boolean equals(Object obj) {
        return obj != null && obj instanceof Tag ? ((Tag)obj).render().equals(this.render()) : false;
    }

    public T withClasses(String... classes) {
        StringBuilder sb = new StringBuilder();
        String[] var3 = classes;
        int var4 = classes.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String s = var3[var5];
            sb.append(s != null ? s : "").append(" ");
        }

        return this.attr("class", sb.toString().trim());
    }

    public T isAutoComplete() {
        return this.attr("autocomplete", (Object)null);
    }

    public T isAutoFocus() {
        return this.attr("autofocus", (Object)null);
    }

    public T isHidden() {
        return this.attr("hidden", (Object)null);
    }

    public T isRequired() {
        return this.attr("required", (Object)null);
    }

    public T withAlt(String alt) {
        return this.attr("alt", alt);
    }

    public T withAction(String action) {
        return this.attr("action", action);
    }

    public T withCharset(String charset) {
        return this.attr("charset", charset);
    }

    public T withClass(String className) {
        return this.attr("class", className);
    }

    public T withContent(String content) {
        return this.attr("content", content);
    }

    public T withDir(String dir) {
        return this.attr("dir", dir);
    }

    public T withHref(String href) {
        return this.attr("href", href);
    }

    public T withId(String id) {
        return this.attr("id", id);
    }

    public T withData(String dataAttr, String value) {
        return this.attr("data-" + dataAttr, value);
    }

    public T withLang(String lang) {
        return this.attr("lang", lang);
    }

    public T withMethod(String method) {
        return this.attr("method", method);
    }

    public T withName(String name) {
        return this.attr("name", name);
    }

    public T withPlaceholder(String placeholder) {
        return this.attr("placeholder", placeholder);
    }

    public T withTarget(String target) {
        return this.attr("target", target);
    }

    public T withTitle(String title) {
        return this.attr("title", title);
    }

    public T withType(String type) {
        return this.attr("type", type);
    }

    public T withRel(String rel) {
        return this.attr("rel", rel);
    }

    public T withRole(String role) {
        return this.attr("role", role);
    }

    public T withSrc(String src) {
        return this.attr("src", src);
    }

    public T withStyle(String style) {
        return this.attr("style", style);
    }

    public T withValue(String value) {
        return this.attr("value", value);
    }

    public T withCondAutoComplete(boolean condition) {
        return this.condAttr(condition, "autocomplete", (String)null);
    }

    public T withCondAutoFocus(boolean condition) {
        return this.condAttr(condition, "autofocus", (String)null);
    }

    public T withCondHidden(boolean condition) {
        return this.condAttr(condition, "hidden", (String)null);
    }

    public T withCondRequired(boolean condition) {
        return this.condAttr(condition, "required", (String)null);
    }

    public T withCondAlt(boolean condition, String alt) {
        return this.condAttr(condition, "alt", alt);
    }

    public T withCondAction(boolean condition, String action) {
        return this.condAttr(condition, "action", action);
    }

    public T withCharset(boolean condition, String charset) {
        return this.condAttr(condition, "charset", charset);
    }

    public T withCondClass(boolean condition, String className) {
        return this.condAttr(condition, "class", className);
    }

    public T withCondContent(boolean condition, String content) {
        return this.condAttr(condition, "content", content);
    }

    public T withCondDir(boolean condition, String dir) {
        return this.condAttr(condition, "dir", dir);
    }

    public T withCondHref(boolean condition, String href) {
        return this.condAttr(condition, "href", href);
    }

    public T withCondId(boolean condition, String id) {
        return this.condAttr(condition, "id", id);
    }

    public T withCondData(boolean condition, String dataAttr, String value) {
        return this.condAttr(condition, "data-" + dataAttr, value);
    }

    public T withCondLang(boolean condition, String lang) {
        return this.condAttr(condition, "lang", lang);
    }

    public T withCondMethod(boolean condition, String method) {
        return this.condAttr(condition, "method", method);
    }

    public T withCondName(boolean condition, String name) {
        return this.condAttr(condition, "name", name);
    }

    public T withCondPlaceholder(boolean condition, String placeholder) {
        return this.condAttr(condition, "placeholder", placeholder);
    }

    public T withCondTarget(boolean condition, String target) {
        return this.condAttr(condition, "target", target);
    }

    public T withCondTitle(boolean condition, String title) {
        return this.condAttr(condition, "title", title);
    }

    public T withCondType(boolean condition, String type) {
        return this.condAttr(condition, "type", type);
    }

    public T withCondRel(boolean condition, String rel) {
        return this.condAttr(condition, "rel", rel);
    }

    public T withCondSrc(boolean condition, String src) {
        return this.condAttr(condition, "src", src);
    }

    public T withCondStyle(boolean condition, String style) {
        return this.condAttr(condition, "style", style);
    }

    public T withCondValue(boolean condition, String value) {
        return this.condAttr(condition, "value", value);
    }
}
