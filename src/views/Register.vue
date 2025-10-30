<template>
  <PageScaffold :meteor-count="25">
    <div class="container mx-auto px-4 py-12 relative z-10">
      <!-- Breadcrumb Navigation -->
      <nav class="mb-8 pt-4" aria-label="Breadcrumb">
        <ol class="flex items-center space-x-2 text-sm">
          <li>
            <router-link 
              to="/" 
              class="text-slate-400 hover:text-white transition-colors duration-300 flex items-center"
            >
              <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path>
              </svg>
              Home
            </router-link>
          </li>
          <li class="text-slate-500">/</li>
          <li class="text-white">Register</li>
        </ol>
      </nav>

      <!-- Registration Form Container -->
      <div class="max-w-4xl mx-auto">
        <div class="bg-gradient-to-br from-slate-900/95 to-slate-800/95 backdrop-blur-sm rounded-2xl shadow-2xl p-8 border border-amber-500/30">
          <!-- Title -->
          <div class="text-center mb-8">
            <h1 class="text-4xl font-bold text-white mb-2">User Registration</h1>
            <p class="text-slate-300">Create Your Account</p>
          </div>

          <!-- Registration Form -->
          <form @submit.prevent="handleRegister" class="space-y-6">
            <!-- General Error Message -->
            <div v-if="fieldErrors.general" class="bg-red-500/10 border border-red-500/30 rounded-xl p-4">
              <div class="flex items-center gap-2">
                <svg class="w-5 h-5 text-red-400 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01M5.07 19h13.86A2 2 0 0021 17.07L13.41 4.93a2 2 0 00-3.42 0L3 17.07A2 2 0 005.07 19z"/>
                </svg>
                <p class="text-red-400 font-medium">{{ fieldErrors.general }}</p>
              </div>
            </div>

            <!-- Basic Information -->
            <div class="bg-slate-800/50 rounded-xl p-6 border border-slate-700">
              <h2 class="text-xl font-semibold text-white mb-4 flex items-center">
                <svg class="w-6 h-6 mr-2 text-amber-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
                </svg>
                Basic Information
              </h2>
              
              <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <!-- Username -->
                <div>
                  <label class="form-label">Username <span class="text-red-400">*</span></label>
                  <input v-model="formData.userName" data-field="userName" type="text" required :class="['form-control', fieldErrors.userName && 'input-warning']" placeholder="Enter username" />
                  <p class="form-help">3-20 characters</p>
                  <p v-if="fieldErrors.userName" class="form-help field-hint-warning">{{ fieldErrors.userName }}</p>
                </div>

                <!-- Email -->
                <div>
                  <label class="form-label">Email <span class="text-red-400">*</span></label>
                  <input v-model="formData.userEmail" data-field="userEmail" type="text" autocomplete="email" required :class="['form-control', fieldErrors.userEmail && 'input-warning']" placeholder="your.email@example.com" />
                  <p class="form-help">For notifications and password recovery</p>
                  <p v-if="fieldErrors.userEmail" class="form-help field-hint-warning">{{ fieldErrors.userEmail }}</p>
                </div>

                <!-- Password -->
                <div>
                  <label class="form-label">Password <span class="text-red-400">*</span></label>
                  <input v-model="formData.userPassword" data-field="userPassword" type="password" autocomplete="new-password" required :class="['form-control', fieldErrors.userPassword && 'input-warning']" placeholder="At least 8 characters" />
                  <p class="form-help">Include letters and numbers</p>
                  <p v-if="fieldErrors.userPassword" class="form-help field-hint-warning">{{ fieldErrors.userPassword }}</p>
                </div>

                <!-- Confirm Password -->
                <div>
                  <label class="form-label">Confirm Password <span class="text-red-400">*</span></label>
                  <input v-model="formData.confirmPassword" data-field="confirmPassword" type="password" autocomplete="new-password" required :class="['form-control', fieldErrors.confirmPassword && 'input-warning']" placeholder="Re-enter password" />
                  <p v-if="fieldErrors.confirmPassword" class="form-help field-hint-warning">{{ fieldErrors.confirmPassword }}</p>
                </div>

                <!-- Real Name -->
                <div>
                  <label class="form-label">Full Name <span class="text-red-400">*</span></label>
                  <input v-model="formData.userRealName" data-field="userRealName" type="text" required :class="['form-control', fieldErrors.userRealName && 'input-warning']" placeholder="Enter your full name" />
                  <p v-if="fieldErrors.userRealName" class="form-help field-hint-warning">{{ fieldErrors.userRealName }}</p>
                </div>

                <!-- Phone -->
                <div>
                  <label class="form-label">Phone Number</label>
                  <input v-model="formData.userPhone" type="tel" class="form-control" placeholder="+1 234 567 8900" />
                </div>
              </div>
            </div>

            <!-- Academic Information -->
            <div class="bg-slate-800/50 rounded-xl p-6 border border-slate-700">
              <h2 class="text-xl font-semibold text-white mb-4 flex items-center">
                <svg class="w-6 h-6 mr-2 text-amber-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path d="M12 14l9-5-9-5-9 5 9 5z"></path>
                  <path d="M12 14l6.16-3.422a12.083 12.083 0 01.665 6.479A11.952 11.952 0 0012 20.055a11.952 11.952 0 00-6.824-2.998 12.078 12.078 0 01.665-6.479L12 14z"></path>
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 14l9-5-9-5-9 5 9 5zm0 0l6.16-3.422a12.083 12.083 0 01.665 6.479A11.952 11.952 0 0012 20.055a11.952 11.952 0 00-6.824-2.998 12.078 12.078 0 01.665-6.479L12 14zm-4 6v-7.5l4-2.222"></path>
                </svg>
                Academic Information
              </h2>
              
              <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <!-- Affiliation -->
                <div>
                  <label class="form-label">Affiliation <span class="text-red-400">*</span></label>
                  <input v-model="formData.userAffiliation" data-field="userAffiliation" type="text" required :class="['form-control', fieldErrors.userAffiliation && 'input-warning']" placeholder="e.g., Harvard University" />
                  <p v-if="fieldErrors.userAffiliation" class="form-help field-hint-warning">{{ fieldErrors.userAffiliation }}</p>
                </div>

                <!-- Department -->
                <div>
                  <label class="form-label">Department/Institute</label>
                  <input v-model="formData.userDepartment" type="text" class="form-control" placeholder="e.g., Computer Science" />
                </div>

                <!-- Title -->
                <div>
                  <label class="form-label">Academic Title</label>
                  <select v-model="formData.userTitle" class="select cursor-pointer">
                    <option value="">Please select</option>
                    <option value="Professor">Professor</option>
                    <option value="Associate Professor">Associate Professor</option>
                    <option value="Lecturer">Lecturer</option>
                    <option value="Research Fellow">Research Fellow</option>
                    <option value="Assistant Research Fellow">Assistant Research Fellow</option>
                    <option value="Student">Student</option>
                    <option value="Other">Other</option>
                  </select>
                </div>

                <!-- Position -->
                <div>
                  <label class="form-label">Position</label>
                  <input v-model="formData.userPosition" type="text" class="form-control" placeholder="e.g., Department Chair, Lab Director" />
                </div>

                <!-- ORCID -->
                <div>
                  <label class="form-label">ORCID</label>
                  <input v-model="formData.userOrcid" type="text" class="form-control" placeholder="0000-0000-0000-0000" />
                  <p class="form-help">
                    <a href="https://orcid.org/" target="_blank" class="text-amber-400 hover:underline">Get ORCID</a>
                  </p>
                </div>

                <!-- Country -->
                <div>
                  <label class="form-label">Country/Region <span class="text-red-400">*</span></label>
                  <div class="relative country-dropdown-container">
                    <!-- Search Input -->
                    <input v-model="countrySearch" @focus="onCountryInputFocus" @input="filterCountries" type="text" required class="form-control" placeholder="Search or select country" />
                    <svg class="w-5 h-5 absolute right-3 top-1/2 -translate-y-1/2 text-slate-400 pointer-events-none" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                    </svg>
                    
                    <!-- Dropdown -->
                    <div 
                      v-if="showCountryDropdown"
                      class="absolute z-20 w-full mt-2 bg-slate-800 border border-slate-600 rounded-lg shadow-2xl max-h-64 overflow-y-auto"
                    >
                      <div 
                        v-for="country in filteredCountries" 
                        :key="country.isoCode"
                        @click="selectCountry(country)"
                        class="px-4 py-2 hover:bg-slate-700 cursor-pointer transition-colors"
                        :class="{ 'bg-slate-700': selectedCountryCode === country.isoCode }"
                      >
                        <span class="text-white">{{ country.name }}</span>
                        <span v-if="country.isoCode === 'CN'" class="ml-2 text-xs text-amber-400">(Default)</span>
                      </div>
                      <div v-if="filteredCountries.length === 0" class="px-4 py-3 text-slate-400 text-sm text-center">
                        No countries found
                      </div>
                    </div>
                  </div>
                </div>

                <!-- Research Areas (Multi-select) -->
                <div class="md:col-span-2">
                  <label class="form-label">Research Areas <span class="text-red-400">*</span> <span class="text-xs text-slate-400 ml-2">(Select up to 5)</span></label>
                  <div class="relative area-dropdown-container">
                    <div 
                      @click="showAreaDropdown = !showAreaDropdown"
                      class="w-full px-4 py-3 bg-slate-900/50 border border-slate-600 rounded-lg text-white cursor-pointer hover:border-amber-500 transition-colors min-h-[48px] flex items-center justify-between"
                    >
                      <div class="flex flex-wrap gap-2">
                        <span v-if="selectedAreas.length === 0" class="text-slate-400">
                          Select research areas
                        </span>
                        <span 
                          v-for="areaId in selectedAreas" 
                          :key="areaId"
                          class="inline-flex items-center gap-1 px-2 py-1 bg-amber-600/20 text-amber-300 rounded text-sm border border-amber-600/30"
                        >
                          {{ getAreaName(areaId) }}
                          <button 
                            type="button"
                            @click.stop="removeArea(areaId)"
                            class="hover:text-amber-100"
                          >
                            <svg class="w-3 h-3" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                            </svg>
                          </button>
                        </span>
                      </div>
                      <svg class="w-5 h-5 text-slate-400 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7"></path>
                      </svg>
                    </div>
                    
                    <!-- Dropdown -->
                    <div 
                      v-if="showAreaDropdown"
                      class="absolute z-10 w-full mt-2 bg-slate-800 border border-slate-600 rounded-lg shadow-2xl max-h-96 overflow-y-auto"
                    >
                      <div v-for="subject in subjects" :key="subject.subjectId" class="border-b border-slate-700 last:border-0">
                        <!-- Subject Header (Clickable) -->
                        <div 
                          @click="toggleSubject(subject.subjectId)"
                          class="px-4 py-3 bg-slate-700/50 font-semibold text-amber-300 text-sm cursor-pointer hover:bg-slate-700 transition-colors flex items-center justify-between"
                        >
                          <span>{{ subject.subjectName }}</span>
                          <svg 
                            class="w-4 h-4 transition-transform duration-200"
                            :class="{ 'rotate-90': isSubjectExpanded(subject.subjectId) }"
                            fill="none" 
                            stroke="currentColor" 
                            viewBox="0 0 24 24"
                          >
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
                          </svg>
                        </div>
                        <!-- Areas (Collapsible) -->
                        <div 
                          v-if="isSubjectExpanded(subject.subjectId)"
                          class="p-2"
                        >
                          <label 
                            v-for="area in getAreasBySubject(subject.subjectId)" 
                            :key="area.areaId"
                            class="flex items-center px-3 py-2 hover:bg-slate-700/50 rounded cursor-pointer transition-colors"
                          >
                            <input
                              type="checkbox"
                              :value="area.areaId"
                              :checked="selectedAreas.includes(area.areaId)"
                              @change="toggleArea(area.areaId)"
                              @click.stop
                              :disabled="!selectedAreas.includes(area.areaId) && selectedAreas.length >= 5"
                              class="w-4 h-4 text-amber-600 bg-slate-900 border-slate-600 rounded focus:ring-amber-500 cursor-pointer"
                            />
                            <span class="ml-3 text-sm text-slate-300">{{ area.areaName }}</span>
                          </label>
                        </div>
                      </div>
                    </div>
                  </div>
                  <p class="form-help">
                    {{ selectedAreas.length }} / 5 selected
                  </p>
                </div>

                <!-- Research Keywords -->
                <div class="md:col-span-2">
                  <label class="form-label">Research Keywords <span class="text-red-400">*</span></label>
                  <input v-model="formData.userKeywords" data-field="userKeywords" type="text" required :class="['form-control', fieldErrors.userKeywords && 'input-warning']" placeholder="Comma separated, e.g., Deep Learning, Computer Vision, NLP" />
                  <p v-if="fieldErrors.userKeywords" class="form-help field-hint-warning">{{ fieldErrors.userKeywords }}</p>
                </div>

                <!-- Biography -->
                <div class="md:col-span-2">
                  <label class="form-label">Biography <span class="text-red-400">*</span></label>
                  <textarea v-model="formData.userBiography" data-field="userBiography" rows="4" required :class="['form-control resize-none', fieldErrors.userBiography && 'input-warning']" placeholder="Brief introduction of your academic background and research interests..."></textarea>
                  <p v-if="fieldErrors.userBiography" class="form-help field-hint-warning">{{ fieldErrors.userBiography }}</p>
                </div>
              </div>
            </div>

            <!-- Address Information -->
            <div class="bg-slate-800/50 rounded-xl p-6 border border-slate-700">
              <h2 class="text-xl font-semibold text-white mb-4 flex items-center">
                <svg class="w-6 h-6 mr-2 text-amber-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z"></path>
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z"></path>
                </svg>
                Address Information
              </h2>
              
              <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                <!-- State/Province -->
                <div>
                  <label class="form-label">State/Province</label>
                  <select v-model="formData.userState" @change="onStateChange" :disabled="!selectedCountryCode" class="select cursor-pointer disabled:opacity-50 disabled:cursor-not-allowed">
                    <option value="">Select state/province</option>
                    <option v-for="state in availableStates" :key="state.isoCode" :value="state.name">
                      {{ state.name }}
                    </option>
                  </select>
                  <p v-if="!selectedCountryCode" class="mt-1 text-xs text-slate-400">Please select country first</p>
                </div>

                <!-- City -->
                <div>
                  <label class="form-label">City</label>
                  <select v-model="formData.userCity" :disabled="!selectedStateCode" class="select cursor-pointer disabled:opacity-50 disabled:cursor-not-allowed">
                    <option value="">Select city</option>
                    <option v-for="city in availableCities" :key="city.name" :value="city.name">
                      {{ city.name }}
                    </option>
                  </select>
                  <p v-if="!selectedStateCode" class="mt-1 text-xs text-slate-400">Please select state/province first</p>
                </div>

                <!-- Postal Code -->
                <div>
                  <label class="form-label">Postal Code</label>
                  <input v-model="formData.userPostalCode" type="text" class="form-control" placeholder="e.g., 94102" />
                </div>

                <!-- Detailed Address -->
                <div class="md:col-span-2">
                  <label class="form-label">Detailed Address</label>
                  <textarea v-model="formData.userAddress" rows="2" class="form-control resize-none" placeholder="Enter your detailed mailing address"></textarea>
                </div>
              </div>
            </div>

            <!-- Terms Agreement -->
            <div class="flex items-start">
              <input v-model="agreedToTerms" type="checkbox" id="terms" class="mt-1 checkbox" />
              <label for="terms" class="ml-3 text-sm text-slate-300">
                I have read and agree to the
                <a href="#" class="text-amber-400 hover:underline">Terms of Service</a>
                and
                <a href="#" class="text-amber-400 hover:underline">Privacy Policy</a>
              </label>
            </div>

            <!-- Submit Buttons -->
            <div class="flex flex-col sm:flex-row gap-4 pt-4">
              <div class="flex-1">
                <ShimmerButton
                  variant="primary"
                :disabled="!canSubmit || submitting"
                  @click="handleRegister"
                  class="w-full"
                >
                  <svg v-if="!submitting" class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M18 9v3m0 0v3m0-3h3m-3 0h-3m-2-5a4 4 0 11-8 0 4 4 0 018 0zM3 20a6 6 0 0112 0v1H3v-1z"></path>
                  </svg>
                  <svg v-else class="animate-spin h-5 w-5" fill="none" viewBox="0 0 24 24">
                    <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                    <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                  </svg>
                  <span v-if="!submitting" class="font-semibold">Create Account</span>
                  <span v-else>Creating...</span>
                </ShimmerButton>
              </div>

              <div class="flex-1">
                <ShimmerButton
                  variant="secondary"
                  @click="$router.push('/login')"
                  class="w-full"
                >
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1"></path>
                  </svg>
                  <span class="font-semibold">Already have an account?</span>
                </ShimmerButton>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </PageScaffold>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted, nextTick, watch } from 'vue'
import { useRouter } from 'vue-router'
import { authApi, subjectApi } from '@/api'
import PageScaffold from '@/components/layout/PageScaffold.vue'
import ShimmerButton from '@/components/ui/ShimmerButton.vue'
import { Country, State, City } from 'country-state-city'

const router = useRouter()
const submitting = ref(false)
const agreedToTerms = ref(false)
const showAreaDropdown = ref(false)
const fieldErrors = ref({})

// Research Areas Data
const subjects = ref([])
const areas = ref([])
const selectedAreas = ref([])
const expandedSubjects = ref(new Set()) // Track which subjects are expanded

// Location Data
const allCountries = Country.getAllCountries()
// Put China at the top
const chinaIndex = allCountries.findIndex(c => c.isoCode === 'CN')
const china = chinaIndex >= 0 ? allCountries[chinaIndex] : null
const otherCountries = allCountries.filter(c => c.isoCode !== 'CN')
const countries = ref(china ? [china, ...otherCountries] : allCountries)

const selectedCountryCode = ref('CN') // Default to China
const selectedCountryName = ref('China')
const selectedStateCode = ref('')
const availableStates = ref([])
const availableCities = ref([])
const showCountryDropdown = ref(false)
const countrySearch = ref('')
const filteredCountries = ref(countries.value)

const formData = ref({
  userName: '',
  userEmail: '',
  userPassword: '',
  confirmPassword: '',
  userRealName: '',
  userPhone: '',
  userAffiliation: '',
  userDepartment: '',
  userTitle: '',
  userPosition: '',
  userOrcid: '',
  userCountry: '',
  userClassification: '',
  userKeywords: '',
  userBiography: '',
  userState: '',
  userCity: '',
  userPostalCode: '',
  userAddress: ''
})

const safeFormData = computed(() => formData.value || {})

const canSubmit = computed(() => {
  const data = safeFormData.value
  return data.userName &&
         data.userEmail &&
         data.userPassword &&
         data.confirmPassword &&
         data.userPassword === data.confirmPassword &&
         data.userRealName &&
         data.userAffiliation &&
         data.userCountry &&
         data.userKeywords &&
         data.userBiography &&
         selectedAreas.value.length > 0 &&
         agreedToTerms.value
})

const extractList = (response) => {
  const res = response?.data ?? {}
  const payload = res.data ?? res

  if (Array.isArray(payload)) {
    return payload
  }

  if (Array.isArray(payload?.data)) {
    return payload.data
  }

  if (Array.isArray(payload?.rows)) {
    return payload.rows
  }

  return []
}

let removeGlobalClickListener

// Load research areas and initialize defaults
onMounted(async () => {
  try {
    const [subjectResponse, areaResponse] = await Promise.all([
      subjectApi.getSubjectList(),
      subjectApi.getAllAreas()
    ])

    subjects.value = extractList(subjectResponse)
    areas.value = extractList(areaResponse)
  } catch (error) {
    console.error('Failed to load research areas:', error)
    subjects.value = []
    areas.value = []
  }
  
  // Initialize default country (China)
  if (selectedCountryCode.value === 'CN') {
    const china = countries.value.find(c => c.isoCode === 'CN')
    if (china) {
      formData.value.userCountry = china.name
      countrySearch.value = china.name
      availableStates.value = State.getStatesOfCountry('CN')
    }
  }
  
  // Close dropdowns when clicking outside
  const clickHandler = (e) => {
    const target = e.target
    // Close area dropdown
    if (!target.closest('.area-dropdown-container')) {
      showAreaDropdown.value = false
    }
    // Close country dropdown
    if (!target.closest('.country-dropdown-container')) {
      showCountryDropdown.value = false
    }
  }
  document.addEventListener('click', clickHandler)
  removeGlobalClickListener = () => document.removeEventListener('click', clickHandler)
})

onUnmounted(() => {
  if (removeGlobalClickListener) {
    removeGlobalClickListener()
  }
})

const getAreasBySubject = (subjectId) => {
  if (!Array.isArray(areas.value)) {
    return []
  }
  return areas.value.filter(area => area.areaSubjectId === subjectId)
}

const toggleSubject = (subjectId) => {
  if (expandedSubjects.value.has(subjectId)) {
    expandedSubjects.value.delete(subjectId)
  } else {
    expandedSubjects.value.add(subjectId)
  }
}

const isSubjectExpanded = (subjectId) => {
  return expandedSubjects.value.has(subjectId)
}

const getAreaName = (areaId) => {
  const area = areas.value.find(a => a.areaId === areaId)
  return area ? area.areaName : ''
}

const toggleArea = (areaId) => {
  const index = selectedAreas.value.indexOf(areaId)
  if (index > -1) {
    selectedAreas.value.splice(index, 1)
  } else if (selectedAreas.value.length < 5) {
    selectedAreas.value.push(areaId)
  }
}

const removeArea = (areaId) => {
  const index = selectedAreas.value.indexOf(areaId)
  if (index > -1) {
    selectedAreas.value.splice(index, 1)
  }
}

// Country search and select
const onCountryInputFocus = () => {
  showCountryDropdown.value = true
  filteredCountries.value = countries.value
}

const selectCountry = (country) => {
  selectedCountryCode.value = country.isoCode
  selectedCountryName.value = country.name
  formData.value.userCountry = country.name
  countrySearch.value = country.name
  showCountryDropdown.value = false
  
  // Load states for selected country
  availableStates.value = State.getStatesOfCountry(country.isoCode)
  
  // Reset state and city
  formData.value.userState = ''
  formData.value.userCity = ''
  selectedStateCode.value = ''
  availableCities.value = []
}

const filterCountries = () => {
  const search = countrySearch.value.toLowerCase()
  if (!search) {
    filteredCountries.value = countries.value
  } else {
    filteredCountries.value = countries.value.filter(country => 
      country.name.toLowerCase().includes(search)
    )
  }
  showCountryDropdown.value = true
}

// State change handler
const onStateChange = () => {
  if (formData.value.userState) {
    // Find state code
    const state = availableStates.value.find(s => s.name === formData.value.userState)
    selectedStateCode.value = state ? state.isoCode : ''
    
    // Load cities for selected state
    if (selectedStateCode.value && selectedCountryCode.value) {
      availableCities.value = City.getCitiesOfState(selectedCountryCode.value, selectedStateCode.value)
    }
    
    // Reset city
    formData.value.userCity = ''
  } else {
    selectedStateCode.value = ''
    availableCities.value = []
    formData.value.userCity = ''
  }
}

const scrollToFirstError = async () => {
  await nextTick()
  if (!fieldErrors.value) return
  const keys = Object.keys(fieldErrors.value)
  if (!keys.length) return
  const el = document.querySelector(`[data-field="${keys[0]}"]`)
  if (el && typeof el.scrollIntoView === 'function') {
    el.scrollIntoView({ behavior: 'smooth', block: 'center' })
  }
}

// 实时提示：密码不一致
watch(
  () => [formData.value.userPassword, formData.value.confirmPassword],
  ([pwd, confirm]) => {
    if (!fieldErrors.value) fieldErrors.value = {}
    if (pwd && confirm && pwd !== confirm) {
      fieldErrors.value.confirmPassword = 'Passwords do not match'
    } else {
      if (fieldErrors.value.confirmPassword) delete fieldErrors.value.confirmPassword
    }
  }
)

const validateForm = () => {
  fieldErrors.value = {}

  const data = safeFormData.value

  // Username validation
  if (!data.userName || data.userName.length < 3 || data.userName.length > 20) {
    fieldErrors.value.userName = 'Username must be 3-20 characters'
  }

  // Email validation
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!emailRegex.test(data.userEmail)) {
    fieldErrors.value.userEmail = 'Please enter a valid email address'
  }

  // Password validation
  if (!data.userPassword || data.userPassword.length < 8) {
    fieldErrors.value.userPassword = 'Password must be at least 8 characters'
  } else {
  const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d).{8,}$/
    if (!passwordRegex.test(data.userPassword)) {
      fieldErrors.value.userPassword = 'Password must contain both letters and numbers'
    }
  }

  // Password match validation
  if (data.userPassword !== data.confirmPassword) {
    fieldErrors.value.confirmPassword = 'Passwords do not match'
  }

  // Requireds
  if (!data.userRealName) fieldErrors.value.userRealName = 'Full Name is required'
  if (!data.userAffiliation) fieldErrors.value.userAffiliation = 'Affiliation is required'
  if (!data.userCountry) fieldErrors.value.userCountry = 'Country/Region is required'
  if (selectedAreas.value.length === 0) fieldErrors.value.userClassification = 'Select at least one research area'
  if (!data.userKeywords) fieldErrors.value.userKeywords = 'Keywords are required'
  if (!data.userBiography) fieldErrors.value.userBiography = 'Biography is required'

  const valid = Object.keys(fieldErrors.value).length === 0
  if (!valid) {
    // Show a general error message for validation failures
    if (!fieldErrors.value.general) {
      fieldErrors.value.general = 'Please correct the highlighted fields below.'
    }
    scrollToFirstError()
  }
  return valid
}

const applyServerError = (message, details) => {
  if (!fieldErrors.value) fieldErrors.value = {}
  if (details && typeof details === 'object') {
    fieldErrors.value = { ...fieldErrors.value, ...details }
  } else {
    fieldErrors.value = { ...fieldErrors.value, general: message }
  }
  scrollToFirstError()
}

const clearErrors = () => {
  fieldErrors.value = {}
}

const handleRegister = async () => {
  // Clear general error message
  if (fieldErrors.value) {
    delete fieldErrors.value.general
  }

  if (!validateForm()) {
    return
  }

  submitting.value = true
  
  try {
  // Convert selected areas to comma-separated string
  const areaNames = selectedAreas.value.map(id => getAreaName(id)).join(', ')
  formData.value.userClassification = areaNames

    const response = await authApi.register({
      username: formData.value.userName,
      email: formData.value.userEmail,
      password: formData.value.userPassword,
      realName: formData.value.userRealName,
      phone: formData.value.userPhone,
      affiliation: formData.value.userAffiliation,
      department: formData.value.userDepartment,
      title: formData.value.userTitle,
      position: formData.value.userPosition,
      orcid: formData.value.userOrcid,
      country: formData.value.userCountry,
      classification: formData.value.userClassification,
      keywords: formData.value.userKeywords,
      biography: formData.value.userBiography,
      state: formData.value.userState,
      city: formData.value.userCity,
      postalCode: formData.value.userPostalCode,
      address: formData.value.userAddress
    })
    
    if (response && (response.code !== undefined ? response.code === 200 : true)) {
      // 预填登录页（系统基于 email 登录）
      localStorage.setItem('rememberedUsername', formData.value.userEmail)
      localStorage.setItem('rememberedPassword', formData.value.userPassword)
      router.push('/login')
    } else {
      const message = response?.msg || response?.message || 'Registration failed, please check the highlighted fields.'
      applyServerError(message, response?.data)
    }
  } catch (error) {
    console.error('Registration error:', error)
    const message = error?.payload?.msg || error?.message || 'Registration failed, please check the highlighted fields.'
    applyServerError(message, error?.payload?.data)
  } finally {
    submitting.value = false
  }
}
</script>

<style scoped>
/* Custom scrollbar */
::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-track {
  background: rgba(15, 23, 42, 0.5);
}

::-webkit-scrollbar-thumb {
  background: rgba(251, 191, 36, 0.5);
  border-radius: 4px;
}

::-webkit-scrollbar-thumb:hover {
  background: rgba(251, 191, 36, 0.7);
}

/* Harmonize labels with card style */
.form-label {
  color: #cbd5e1 !important; /* slate-300 */
}
</style>
