package jp.gr.java_conf.frontier.utauplug

import org.specs2.mutable._
import org.specs2.runner.JUnitRunner
import org.junit.runner.RunWith
import java.io.File

@RunWith(classOf[JUnitRunner])
class UtauPlugSpec extends Specification {

  "fromFile" should {
    val a = "src/test/scala/jp/gr/java_conf/frontier/utauplug/UtauPlug_test.ust"
    val plug = UtauPlug.fromFile(a)

    "��ڂ̗v�f���擾�ł��Ă��邱��" in {
      plug.list.size must_== 22
      plug.list(0).lyric must_== "��"
      plug.list(0).blockName must_== "#0004"
    }
    "�v�f�����ԂɎ擾�ł��Ă��邱��" in {
      plug.list(0).lyric must_== "��"
      plug.list(1).lyric must_== "��"
      plug.list(2).lyric must_== "��"
      plug.list(3).lyric must_== "R"
    }
  }

  "node" should {
    val a = "src/test/scala/jp/gr/java_conf/frontier/utauplug/UtauPlug_test.ust"
    val plug = UtauPlug.fromFile(a)
    "prev�̈�O���擾���悤�Ƃ����Ƃ�" in {
      plug.node(0).prev.prev.isEmpty must_== true
    }
    "��ڂ̃m�[�h����PREV�擾" in {
      plug.node(0).prev.get.lyric must_== "��"
    }
    "��ڂ̃m�[�h�擾" in {
      plug.node(0).get.lyric must_== "��"
    }
    "��ڂ̃m�[�h����O�̃m�[�h�擾" in {
      plug.node(1).prev.get.lyric must_== "��"
    }
    "��ڂ̃m�[�h���玟�̃m�[�h�擾" in {
      plug.node(0).next.get.lyric must_== "��"
    }
    "�Ō�̃m�[�h���玟�̃m�[�h�iNEXT�j�擾" in {
      plug.node(plug.list.size - 1).next.get.lyric must_== "��"
    }

  }
  //
  //  //map�̂ق�����������
  //  "foreach" should {
  //    val a = "src/test/scala/jp/gr/java_conf/frontier/utauplug/UtauPlug_test.ust"
  //    val plug = UtauPlug.fromFile(a)
  //
  //    "intensity + 10" in {
  //      plug.node(2).get.intensity must_== 100
  //      for(n <-plug){
  //        n.get.intensity += 10
  //      }
  //      plug.node(2).get.intensity must_== 110
  //
  //    }
  //  }

  "exec" should {
    val a = "src/test/scala/jp/gr/java_conf/frontier/utauplug/UtauPlug_test.ust"
    val plug = UtauPlug.fromFile(a)

    "intensity + 10" in {
      plug.node(2).get.intensity must_== 100
      val plug2 = plug.exec { e =>
        val b = e.node.get.builder
        b.intensity += 10
        e.add(b.build)
        e
      }
      plug2.node(2).get.intensity must_== 110
    }

    "insert" in {
      val plug2 = plug.exec { e =>
        e.add()
        e.add(new UtauElement(Map("Intensity" -> "10", "Lyric" -> "�Ă�")))
      }
      plug2.node(0).get.intensity must_== 100
      plug2.node(0).get.lyric must_== "��"
      plug2.node(1).get.intensity must_== 10
      plug2.node(1).get.lyric must_== "�Ă�"
      plug2.node(2).get.intensity must_== 100
      plug2.node(2).get.lyric must_== "��"
      plug2.node(3).get.intensity must_== 10
      plug2.node(3).get.lyric must_== "�Ă�"
    }
  }

  //output
  "output" should {
    val a = "src/test/scala/jp/gr/java_conf/frontier/utauplug/UtauPlug_test.ust"
    val plug = UtauPlug.fromFile(a)
    "output" in {
      val p = "output_test.ust"
      val f = new File(p)
      if (f.exists) {
        f.delete()
      }
      plug.output(p)
      val plug2 = UtauPlug.fromFile(p)
      plug2.node(0).get.lyric must_== "��"
    }
  }

}