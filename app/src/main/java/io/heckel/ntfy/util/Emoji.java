package io.heckel.ntfy.util;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

/**
 * This class represents an emoji.
 *
 * This class was originally written by Vincent DURMONT (vdurmont@gmail.com) as part of
 * https://github.com/vdurmont/emoji-java, but has since been heavily stripped and modified.
 */
public class Emoji {
  private final List<String> aliases;
  private final String unicode;

  protected Emoji(List<String> aliases, byte... bytes) {
    this.aliases = Collections.unmodifiableList(aliases);
    try {
      this.unicode = new String(bytes, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public List<String> getAliases() {
    return this.aliases;
  }

  public String getUnicode() {
    return this.unicode;
  }
}
