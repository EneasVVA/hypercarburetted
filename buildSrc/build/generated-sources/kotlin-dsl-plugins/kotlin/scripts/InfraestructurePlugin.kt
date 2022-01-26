package scripts


/**
 * Precompiled [infraestructure.gradle.kts][scripts.Infraestructure_gradle] script plugin.
 *
 * @see scripts.Infraestructure_gradle
 */
class InfraestructurePlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("scripts.Infraestructure_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
