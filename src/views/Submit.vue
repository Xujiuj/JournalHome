<template>
  <PageScaffold :show-progress="true" :meteor-count="25">
    <div class="submit min-h-screen bg-gradient-to-br from-slate-900 via-slate-800 to-blue-900 relative overflow-hidden">
      <div class="container relative z-10">
        <!-- Page Header -->
        <div class="relative z-10 py-20">
          <div class="container mx-auto px-4">
            <div class="text-center">
              <h1 class="text-4xl lg:text-5xl xl:text-6xl font-bold text-white leading-tight font-serif mb-6 animate-fade-in drop-shadow-2xl">Submit Paper</h1>
              <p class="text-lg lg:text-xl text-slate-100 leading-relaxed mb-8 animate-slide-up drop-shadow-lg">Submit your research paper for publication consideration</p>
            </div>
          </div>
        </div>

        <!-- Login Required Notice -->
        <div v-if="!isAuthenticated" class="relative z-10 py-20">
          <div class="container mx-auto px-4">
            <div class="max-w-2xl mx-auto">
              <div class="smooth-card smooth-card-red animate-slide-up text-center">
                <h2 class="text-2xl font-bold text-white mb-4">Login Required</h2>
                <p class="text-slate-200 mb-6">You need to be logged in to submit a paper. Please log in or create an account to continue.</p>
                <div class="flex justify-center gap-4">
                  <button @click="showLogin = true" class="group relative inline-flex items-center gap-2 px-6 py-3 bg-gradient-to-r from-cyan-500 via-blue-500 to-indigo-500 text-white rounded-lg font-semibold hover:from-cyan-600 hover:via-blue-600 hover:to-indigo-600 hover:shadow-2xl transition-all duration-700 ease-out overflow-hidden">
                    Login
                    <svg class="w-4 h-4 ml-2 transition-transform duration-500 ease-out group-hover:translate-x-1" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 16l-4-4m0 0l4-4m-4 4h14m-5 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h7a3 3 0 013 3v1"></path>
                    </svg>
                  </button>
                  <button @click="showRegister = true" class="group relative inline-flex items-center gap-2 px-6 py-3 bg-white/10 dark:bg-slate-700/20 text-cyan-400 border border-cyan-400 rounded-lg font-semibold hover:bg-white/20 dark:hover:bg-slate-700/30 hover:shadow-lg transition-all duration-500 ease-out overflow-hidden">
                    Register
                    <svg class="w-4 h-4 ml-2 transition-transform duration-500 ease-out group-hover:rotate-12" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M18 9v3m0 0v3m0-3h3m-3 0h-3m-2-5a4 4 0 11-8 0 4 4 0 018 0zM3 20a6 6 0 0112 0v1H3v-1z"></path>
                    </svg>
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- Submission Form -->
        <div v-else class="relative z-10 py-20">
          <div class="container mx-auto px-4">
            <div class="max-w-4xl mx-auto">
              <div class="smooth-card smooth-card-blue animate-slide-up">
                <form @submit.prevent="submitPaper" class="space-y-8">
                  <div class="form-section">
                    <h2>Paper Information</h2>

                    <div class="form-group">
                      <label for="title">Paper Title *</label>
                      <input
                        id="title"
                        v-model="form.title"
                        type="text"
                        required
                        placeholder="Enter the title of your paper"
                        maxlength="500"
                        :class="[fieldErrors.title && 'input-warning']"
                        data-field="title"
                      />
                      <div class="char-count">{{ form.title.length }}/500</div>
                      <p v-if="fieldErrors.title" class="form-help field-hint-warning">{{ fieldErrors.title }}</p>
                    </div>

                    <div class="form-group">
                      <label for="coverLetter">Cover Letter</label>
                      <textarea
                        id="coverLetter"
                        v-model="form.coverLetter"
                        rows="4"
                        placeholder="Provide a brief cover letter to the editors (optional)"
                      ></textarea>
                    </div>

                    <div class="form-group">
                      <label for="abstract">Abstract *</label>
                      <textarea
                        id="abstract"
                        v-model="form.abstract"
                        required
                        rows="5"
                        placeholder="Provide a comprehensive abstract of your paper (250-300 words)"
                        maxlength="2000"
                        :class="[fieldErrors.abstract && 'input-warning']"
                        data-field="abstract"
                      ></textarea>
                      <div class="char-count">{{ form.abstract.length }}/2000</div>
                      <p v-if="fieldErrors.abstract" class="form-help field-hint-warning">{{ fieldErrors.abstract }}</p>
                    </div>

                    <div class="form-group">
                      <label for="keywords">Keywords *</label>
                      <input
                        id="keywords"
                        v-model="form.keywords"
                        type="text"
                        required
                        placeholder="Enter keywords separated by commas (e.g., machine learning, artificial intelligence, data science)"
                        :class="[fieldErrors.keywords && 'input-warning']"
                        data-field="keywords"
                      />
                      <p v-if="fieldErrors.keywords" class="form-help field-hint-warning">{{ fieldErrors.keywords }}</p>
                    </div>

                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                      <div class="form-group">
                        <label for="manuscriptType">Manuscript Type *</label>
                        <select id="manuscriptType" v-model="form.manuscriptType" required :class="[fieldErrors.manuscriptType && 'input-warning']" data-field="manuscriptType">
                          <option value="">Please select</option>
                          <option value="RESEARCH_ARTICLE">Research Article</option>
                          <option value="REVIEW">Review</option>
                          <option value="SHORT_COMMUNICATION">Short Communication</option>
                          <option value="CASE_STUDY">Case Study</option>
                          <option value="LETTER">Letter</option>
                        </select>
                        <p v-if="fieldErrors.manuscriptType" class="form-help field-hint-warning">{{ fieldErrors.manuscriptType }}</p>
                      </div>

                      <div class="form-group">
                        <label for="subjectArea">Subject Area *</label>
                        <input id="subjectArea" v-model="form.subjectArea" type="text" required placeholder="e.g., Computer Science; AI" :class="[fieldErrors.subjectArea && 'input-warning']" data-field="subjectArea" />
                        <p v-if="fieldErrors.subjectArea" class="form-help field-hint-warning">{{ fieldErrors.subjectArea }}</p>
                      </div>
                    </div>

                    <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
                      <div class="form-group">
                        <label for="wordCount">Word Count *</label>
                        <input id="wordCount" v-model.number="form.wordCount" type="number" min="0" required placeholder="e.g., 5200" :class="[fieldErrors.wordCount && 'input-warning']" data-field="wordCount" />
                        <p v-if="fieldErrors.wordCount" class="form-help field-hint-warning">{{ fieldErrors.wordCount }}</p>
                      </div>
                      <div class="form-group">
                        <label for="figureCount">Figures *</label>
                        <input id="figureCount" v-model.number="form.figureCount" type="number" min="0" required placeholder="e.g., 5" :class="[fieldErrors.figureCount && 'input-warning']" data-field="figureCount" />
                        <p v-if="fieldErrors.figureCount" class="form-help field-hint-warning">{{ fieldErrors.figureCount }}</p>
                      </div>
                      <div class="form-group">
                        <label for="tableCount">Tables *</label>
                        <input id="tableCount" v-model.number="form.tableCount" type="number" min="0" required placeholder="e.g., 3" :class="[fieldErrors.tableCount && 'input-warning']" data-field="tableCount" />
                        <p v-if="fieldErrors.tableCount" class="form-help field-hint-warning">{{ fieldErrors.tableCount }}</p>
                      </div>
                    </div>

                    <div class="form-group">
                      <label for="suggestedReviewers">Suggested Reviewers (JSON)</label>
                      <textarea
                        id="suggestedReviewers"
                        v-model="form.suggestedReviewers"
                        rows="4"
                        placeholder='e.g. [{"suggestedUserId":123,"suggestedReason":"Expert in topic"}]'
                      ></textarea>
                    </div>

                    <div class="form-group">
                      <label for="opposedReviewers">Opposed Reviewers (JSON)</label>
                      <textarea
                        id="opposedReviewers"
                        v-model="form.opposedReviewers"
                        rows="4"
                        placeholder='e.g. [{"opposedUserId":456,"opposedReason":"Conflict of interest"}]'
                      ></textarea>
                    </div>
                  </div>

                  <div class="form-section">
                    <h2>File Upload</h2>

                    <div class="form-group">
                      <label for="file">Paper File *</label>
                      <input
                        id="file"
                        type="file"
                        accept=".pdf,.doc,.docx"
                        @change="handleFileUpload"
                        required
                        :class="[fieldErrors.file && 'input-warning']"
                        data-field="file"
                      />
                      <p class="file-help">Accepted formats: PDF, DOC, DOCX (Max size: 10MB)</p>
                      <p v-if="fieldErrors.file" class="form-help field-hint-warning">{{ fieldErrors.file }}</p>
                    </div>
                  </div>


                  <div class="form-section">
                    <h2>Additional Information</h2>

                    <div class="form-group">
                      <label for="notes">Notes to Editors</label>
                      <textarea id="notes" v-model="form.notes" rows="3" placeholder="Any additional notes (optional)"></textarea>
                    </div>

                    <div class="space-y-3">
                      <label class="checkbox-label">
                        <input type="checkbox" v-model="form.declareOriginal" required :class="[fieldErrors.declareOriginal && 'input-warning']" data-field="declareOriginal" />
                        <span class="checkmark"></span>
                        I declare this submission is original and not under consideration elsewhere.
                      </label>
                      <label class="checkbox-label">
                        <input type="checkbox" v-model="form.declareCorresponding" required :class="[fieldErrors.declareCorresponding && 'input-warning']" data-field="declareCorresponding" />
                        <span class="checkmark"></span>
                        I am the corresponding author and responsible for communication with the journal.
                      </label>
                      <label class="checkbox-label">
                        <input type="checkbox" v-model="form.declareNoConflict" required :class="[fieldErrors.declareNoConflict && 'input-warning']" data-field="declareNoConflict" />
                        <span class="checkmark"></span>
                        All authors declare no competing interests related to this work.
                      </label>
                      <label class="checkbox-label">
                        <input type="checkbox" v-model="form.agreeTerms" required :class="[fieldErrors.agreeTerms && 'input-warning']" data-field="agreeTerms" />
                        <span class="checkmark"></span>
                        I agree to the <a href="#" target="_blank">Terms and Conditions</a> and <a href="#" target="_blank">Publication Ethics</a>
                      </label>
                      <p v-if="fieldErrors.general" class="form-help field-hint-warning">{{ fieldErrors.general }}</p>
                    </div>
                  </div>

                  <div class="form-actions">
                    <button type="button" @click="resetForm" class="btn btn-outline">
                      Reset Form
                    </button>
                    <button type="submit" class="btn btn-primary" :disabled="submitting">
                      <span v-if="submitting">Submitting...</span>
                      <span v-else>Submit Paper</span>
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>

        <!-- Login Modal -->
        <div v-if="showLogin" class="modal-overlay" @click="showLogin = false">
          <div class="modal" @click.stop>
            <div class="modal-header">
              <h3>Login</h3>
              <button class="close-btn" @click="showLogin = false">&times;</button>
            </div>
            <div class="modal-content">
              <form @submit.prevent="handleLogin">
                <div class="form-group">
                  <label>Email</label>
                  <input v-model="loginForm.email" type="email" required />
                </div>
                <div class="form-group">
                  <label>Password</label>
                  <input v-model="loginForm.password" type="password" required />
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
              </form>
            </div>
          </div>
        </div>

        <!-- Register Modal -->
        <div v-if="showRegister" class="modal-overlay" @click="showRegister = false">
          <div class="modal" @click.stop>
            <div class="modal-header">
              <h3>Register</h3>
              <button class="close-btn" @click="showRegister = false">&times;</button>
            </div>
            <div class="modal-content">
              <form @submit.prevent="handleRegister">
                <div class="form-group">
                  <label>Full Name</label>
                  <input v-model="registerForm.name" type="text" required />
                </div>
                <div class="form-group">
                  <label>Email</label>
                  <input v-model="registerForm.email" type="email" required />
                </div>
                <div class="form-group">
                  <label>Password</label>
                  <input v-model="registerForm.password" type="password" required />
                </div>
                <button type="submit" class="btn btn-primary">Register</button>
              </form>
            </div>
          </div>
        </div>

      </div>
    </div>
  </PageScaffold>
</template>

<script setup>
import { ref, reactive, computed } from 'vue'
import { authApi, apiClient } from '@/api'
import PageScaffold from '@/components/layout/PageScaffold.vue'

const isAuthenticated = computed(() => {
  const token = localStorage.getItem('token')
  const user = localStorage.getItem('user')
  return !!(token && user)
})

const showLogin = ref(false)
const showRegister = ref(false)
const submitting = ref(false)

const form = reactive({
  title: '',
  abstract: '',
  keywords: '',
  manuscriptType: '',
  subjectArea: '',
  wordCount: null,
  figureCount: 0,
  tableCount: 0,
  coverLetter: '',
  suggestedReviewers: '',
  opposedReviewers: '',
  file: null,
  notes: '',
  declareOriginal: false,
  declareCorresponding: false,
  declareNoConflict: false,
  agreeTerms: false
})

const fieldErrors = reactive({})

const loginForm = reactive({
  email: '',
  password: ''
})

const registerForm = reactive({
  username: '',
  email: '',
  password: '',
  confirmPassword: '',
  realName: '',
  affiliation: '',
  department: '',
  title: '',
  position: '',
  phone: '',
  fax: '',
  address: '',
  country: '',
  city: '',
  postalCode: '',
  researchAreas: '',
  orcid: '',
  researcherId: '',
  googleScholar: '',
  publons: '',
  scopus: '',
  academicDegrees: '',
  professionalMemberships: '',
  fundingSources: '',
  conflictInterests: '',
  biography: '',
  agreeTerms: false
})

const handleFileUpload = (event) => {
  form.file = event.target.files[0]
}




const scrollToFirstError = () => {
  const firstKey = Object.keys(fieldErrors)[0]
  if (!firstKey) return
  const el = document.querySelector(`[data-field="${firstKey}"]`)
  if (el && typeof el.scrollIntoView === 'function') el.scrollIntoView({ behavior: 'smooth', block: 'center' })
}

const validate = () => {
  Object.keys(fieldErrors).forEach(k => delete fieldErrors[k])
  if (!form.title) fieldErrors.title = 'Title is required'
  if (!form.abstract) fieldErrors.abstract = 'Abstract is required'
  if (!form.keywords) fieldErrors.keywords = 'Keywords are required'
  if (!form.manuscriptType) fieldErrors.manuscriptType = 'Select a manuscript type'
  if (!form.subjectArea) fieldErrors.subjectArea = 'Subject area is required'
  if (form.wordCount == null || form.wordCount < 0) fieldErrors.wordCount = 'Provide word count'
  if (form.figureCount == null || form.figureCount < 0) fieldErrors.figureCount = 'Provide figure count'
  if (form.tableCount == null || form.tableCount < 0) fieldErrors.tableCount = 'Provide table count'
  if (!form.file) fieldErrors.file = 'Manuscript file is required'
  if (!form.declareOriginal) fieldErrors.declareOriginal = 'Required'
  if (!form.declareCorresponding) fieldErrors.declareCorresponding = 'Required'
  if (!form.declareNoConflict) fieldErrors.declareNoConflict = 'Required'
  if (!form.agreeTerms) fieldErrors.agreeTerms = 'You must agree to terms'
  const ok = Object.keys(fieldErrors).length === 0
  if (!ok) scrollToFirstError()
  return ok
}

const submitPaper = async () => {
  if (!validate()) return

  submitting.value = true

  try {
    const formData = new FormData()
    formData.append('title', form.title)
    formData.append('abstract', form.abstract)
    formData.append('keywords', form.keywords)
    formData.append('manuscriptType', form.manuscriptType)
    formData.append('subjectArea', form.subjectArea)
    formData.append('wordCount', String(form.wordCount ?? ''))
    formData.append('figureCount', String(form.figureCount ?? ''))
    formData.append('tableCount', String(form.tableCount ?? ''))
    if (form.coverLetter) formData.append('coverLetter', form.coverLetter)
    if (form.suggestedReviewers) formData.append('suggestedReviewers', form.suggestedReviewers)
    if (form.opposedReviewers) formData.append('opposedReviewers', form.opposedReviewers)
    if (form.file) formData.append('file', form.file)
    if (form.notes) formData.append('notes', form.notes)

    const response = await apiClient.post('/articles/submit', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })

    const isSuccess = response && (response.code !== undefined ? response.code === 200 : true)
    if (isSuccess) {
      resetForm()
    } else {
      const message = response?.msg || response?.message || 'Failed to submit paper.'
      fieldErrors.general = message
      scrollToFirstError()
    }
  } catch (error) {
    fieldErrors.general = error?.message || 'Failed to submit paper. Please try again.'
    console.error('Submit paper error:', error)
    scrollToFirstError()
  } finally {
    submitting.value = false
  }
}

const resetForm = () => {
  // 基本信息
  form.title = ''
  form.abstract = ''
  form.keywords = ''
  form.coverLetter = ''
  // 文件
  form.file = null
  form.suggestedReviewers = ''
  form.opposedReviewers = ''
  form.notes = ''
  form.agreeTerms = false
}

const handleLogin = () => {
  console.log('Login:', loginForm)
  showLogin.value = false
}

const handleRegister = async () => {
  if (registerForm.password !== registerForm.confirmPassword) {
    alert('Passwords do not match.')
    return
  }

  try {
    const response = await authApi.register({
      username: registerForm.username,
      email: registerForm.email,
      password: registerForm.password,
      realName: registerForm.realName,
      affiliation: registerForm.affiliation,
      department: registerForm.department,
      title: registerForm.title,
      position: registerForm.position,
      phone: registerForm.phone,
      fax: registerForm.fax,
      address: registerForm.address,
      country: registerForm.country,
      city: registerForm.city,
      postalCode: registerForm.postalCode,
      researchAreas: registerForm.researchAreas,
      orcid: registerForm.orcid,
      researcherId: registerForm.researcherId,
      googleScholar: registerForm.googleScholar,
      publons: registerForm.publons,
      scopus: registerForm.scopus,
      academicDegrees: registerForm.academicDegrees,
      professionalMemberships: registerForm.professionalMemberships,
      fundingSources: registerForm.fundingSources,
      conflictInterests: registerForm.conflictInterests,
      biography: registerForm.biography
    })

    const isSuccess = response && (response.code !== undefined ? response.code === 200 : true)

    if (isSuccess) {
      alert('Registration successful! Please check your email for verification.')
      showRegister.value = false
      resetRegisterForm()
    } else {
      const message = response?.msg || response?.message || 'Registration failed.'
      alert(message)
    }
  } catch (error) {
    alert('Registration failed. Please try again.')
    console.error('Register error:', error)
  }
}

const resetRegisterForm = () => {
  registerForm.username = ''
  registerForm.email = ''
  registerForm.password = ''
  registerForm.confirmPassword = ''
  registerForm.realName = ''
  registerForm.affiliation = ''
  registerForm.department = ''
  registerForm.title = ''
  registerForm.position = ''
  registerForm.phone = ''
  registerForm.fax = ''
  registerForm.address = ''
  registerForm.country = ''
  registerForm.city = ''
  registerForm.postalCode = ''
  registerForm.researchAreas = ''
  registerForm.orcid = ''
  registerForm.researcherId = ''
  registerForm.googleScholar = ''
  registerForm.publons = ''
  registerForm.scopus = ''
  registerForm.academicDegrees = ''
  registerForm.professionalMemberships = ''
  registerForm.fundingSources = ''
  registerForm.conflictInterests = ''
  registerForm.biography = ''
  registerForm.agreeTerms = false
}
</script>

<style>
@import '../assets/css/submit.css';
@import '../assets/css/utilities.css';

.submit {
  padding: 2rem 0;
  min-height: 100vh;
}

/* Page Header */
.page-header {
  text-align: center;
  margin-bottom: 3rem;
}

.page-header h1 {
  font-size: 2.5rem;
  color: #1f2937;
  margin-bottom: 1rem;
}

.page-header p {
  font-size: 1.1rem;
  color: #6b7280;
}

/* Login Notice */
.login-notice {
  max-width: 600px;
  margin: 0 auto;
  background: white;
  border-radius: 12px;
  padding: 3rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.notice-content h2 {
  color: #1f2937;
  margin-bottom: 1rem;
  font-size: 1.75rem;
}

.notice-content p {
  color: #6b7280;
  margin-bottom: 2rem;
  font-size: 1.1rem;
}

.notice-actions {
  display: flex;
  gap: 1rem;
  justify-content: center;
}

/* Submission Form */
.submission-form {
  max-width: 800px;
  margin: 0 auto;
}

.paper-form {
  background: white;
  border-radius: 12px;
  padding: 2rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.form-section {
  margin-bottom: 3rem;
  padding-bottom: 2rem;
  border-bottom: 1px solid #e5e7eb;
}

.form-section:last-child {
  border-bottom: none;
  margin-bottom: 0;
}

.form-section h2 {
  color: #1f2937;
  margin-bottom: 2rem;
  font-size: 1.5rem;
}

.form-group {
  margin-bottom: 1.5rem;
}

.form-group label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: 500;
  color: #374151;
}

.form-group input,
.form-group textarea,
.form-group select {
  width: 100%;
  padding: 12px;
  border: 2px solid #e5e7eb;
  border-radius: 6px;
  font-size: 1rem;
  transition: border-color 0.5s cubic-bezier(0.23, 1, 0.32, 1);
}

.form-group input:focus,
.form-group textarea:focus,
.form-group select:focus {
  outline: none;
  border-color: #3b82f6;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.1);
}

.char-count {
  text-align: right;
  font-size: 0.8rem;
  color: #6b7280;
  margin-top: 0.25rem;
}

.file-help {
  font-size: 0.9rem;
  color: #6b7280;
  margin-top: 0.5rem;
  margin-bottom: 0;
}

.checkbox-label {
  display: flex;
  align-items: flex-start;
  gap: 0.75rem;
  cursor: pointer;
  font-size: 0.9rem;
  line-height: 1.5;
}

.checkbox-label input[type="checkbox"] {
  width: auto;
  margin: 0;
}

.checkbox-label a {
  color: #3b82f6;
  text-decoration: none;
}

.checkbox-label a:hover {
  text-decoration: underline;
}

.form-actions {
  display: flex;
  gap: 1rem;
  justify-content: flex-end;
  margin-top: 2rem;
}

/* Modal Styles */
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 2000;
}

.modal {
  background: white;
  border-radius: 12px;
  box-shadow: 0 20px 50px rgba(0, 0, 0, 0.3);
  width: 90%;
  max-width: 400px;
  max-height: 90vh;
  overflow-y: auto;
}


/* Responsive */
@media (max-width: 768px) {
  .notice-actions {
    flex-direction: column;
  }
  
  .form-actions {
    flex-direction: column;
  }
  
  .paper-form {
    padding: 1.5rem;
  }
}

@media (max-width: 480px) {
  .page-header h1 {
    font-size: 2rem;
  }
  
  .login-notice {
    padding: 2rem;
  }
}
</style>
