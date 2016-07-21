package io.gitlab.artismarti.smellyj

import com.gitlab.artismarti.smartsmells.api.SmellResult
import com.gitlab.artismarti.smartsmells.common.Smelly

/**
 * @author artur
 */
object SmellRegistry {

	var smellSet: com.gitlab.artismarti.smartsmells.api.SmellResult? = null

	fun register(smellResult: SmellResult) {
		smellSet = smellResult
	}

	fun forPath(path: String): List<Smelly> {
		return smellSet?.filter(path) ?: emptyList()
	}

}