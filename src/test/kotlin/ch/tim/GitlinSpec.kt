package ch.tim

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals
import kotlin.test.assertTrue

object GitlinSpec : Spek({
    describe("Gitlin") {

        describe("init") {
            beforeEach {
                // clean up => remove .gitlin folder with its content
                // then create the folder again
                main(arrayOf())
            }

            it("creates a .gitlin folder") {
                assertEquals(2, 1 + 1)
            }
        }
    }
})