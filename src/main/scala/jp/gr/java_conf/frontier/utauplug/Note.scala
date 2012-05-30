package jp.gr.java_conf.frontier.utauplug

/**
 * ���K�������N���X
 * �f�[�^�I�ɂ͂�����Int�����A�����擾�Ȃǂ̋@�\��񋟂���B
 */
class Note private (val num: Int) {

  /**
   * ���̉��̃I�N�^�[�u�l��Ԃ�
   * @return octave value
   */
  def octave: Int = (num - 12) / 12

  /**
   * ���̉��̉�����Ԃ�
   * @return note name
   */
  def name: String = num % 12 match {
    case 0 => "C"
    case 1 => "C#"
    case 2 => "D"
    case 3 => "D#"
    case 4 => "E"
    case 5 => "F"
    case 6 => "F#"
    case 7 => "G"
    case 8 => "G#"
    case 9 => "A"
    case 10 => "A#"
    case 11 => "B"
  }

  /**
   * ����+�I�N�^�[�u�̕������Ԃ�
   * @return name+octave
   */
  def fullName: String = name + octave

}

/**
 * Factory for [[Note]] instances.
 */
object Note {
  /**
   * ���ԍ�����Note�𐶐����܂��B
   * �ԍ���UTAU�ł̗p��Ɉˑ����܂����A24=C1����̌v�Z�ɂȂ�܂��B
   */
  def apply(num: Int): Note = new Note(num)

  /**
   * �����A�I�N�^�[�u����Note���쐬���܂��B
   * ������A-F�����a-f�A#�i�V���[�v�j�܂���b�i�t���b�g�j�ɑΉ����Ă��܂��B
   */
  def apply(name: String, octave: Int): Note = {
    val num = name match {
      case "C" => 0
      case "C#" => 1
      case "Db" => 1
      case "D" => 2
      case "D#" => 3
      case "Eb" => 3
      case "E" => 4
      case "F" => 5
      case "F#" => 6
      case "Gb" => 6
      case "G" => 7
      case "G#" => 8
      case "Ab" => 8
      case "A" => 9
      case "A#" => 10
      case "Bb" => 10
      case "B" => 11
    }
    new Note(num + ((octave + 1) * 12))
  }

  /**
   * ����+�I�N�^�[�u����Note���쐬���܂��B
   * apply(name, octave)�Ɠ����ł��B
   * @param fullName name+octave�̕����� ��F"C4", "Eb3", "C-2"
   */
  def apply(fullName: String): Note = {
    val m = resolveFullName(fullName)
    apply(m._1, m._2)
  }

  /**
   * ����+�I�N�^�[�u�̕�����𕪉�����
   * apply(fullName)�p�̊֐�
   * @return (note name, octave)
   */
  def resolveFullName(fullName: String): (String, Int) = {
    val r = "([ABCDEFGabcdefg][#b]?)(-?[0-9]+)".r;
    val m = r.findFirstMatchIn(fullName).head
    (m.group(1), m.group(2).toInt)
  }

}