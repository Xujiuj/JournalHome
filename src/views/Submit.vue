<template>
  <PageScaffold :show-progress="true" :meteor-count="25" background-type="dark">
    <div class="submit min-h-screen bg-gradient-to-br from-slate-900 via-blue-900 to-indigo-900 relative overflow-hidden">
      <div class="container relative z-10">
        <!-- Page Header -->
        <div class="relative z-10 py-12">
          <div class="container mx-auto px-4">
            <div class="text-center">
              <h1 class="text-4xl lg:text-5xl font-bold text-white leading-tight font-serif mb-4">Submit Paper</h1>
              <p class="text-lg text-slate-100">Submit your research paper for publication consideration</p>
            </div>
          </div>
        </div>

        <!-- Submission Form -->
        <div class="relative z-10 pb-20">
          <div class="container mx-auto px-4">
            <div class="max-w-4xl mx-auto">
              <!-- Progress Steps -->
              <div class="mb-8">
                <div class="flex items-center justify-between">
                  <div class="flex items-center">
                    <div :class="['w-10 h-10 rounded-full flex items-center justify-center font-semibold transition-all', 
                                 currentStep >= 1 ? 'bg-cyan-500 text-white' : 'bg-slate-600 text-slate-300']">
                      <span v-if="currentStep > 1">✓</span>
                      <span v-else>1</span>
                    </div>
                    <span :class="['ml-3 font-medium', currentStep >= 1 ? 'text-cyan-400' : 'text-slate-400']">
                      Basic Information
                    </span>
                  </div>
                  <div class="flex-1 h-0.5 mx-4" :class="currentStep >= 2 ? 'bg-cyan-500' : 'bg-slate-600'"></div>
                  <div class="flex items-center">
                    <div :class="['w-10 h-10 rounded-full flex items-center justify-center font-semibold transition-all', 
                                 currentStep >= 2 ? 'bg-cyan-500 text-white' : 'bg-slate-600 text-slate-300']">
                      <span v-if="currentStep > 2">✓</span>
                      <span v-else>2</span>
                    </div>
                    <span :class="['ml-3 font-medium', currentStep >= 2 ? 'text-cyan-400' : 'text-slate-400']">
                      File Upload
                    </span>
                  </div>
                </div>
              </div>

              <div class="smooth-card smooth-card-blue animate-slide-up">
                <!-- Step 1: Basic Information -->
                <div v-if="currentStep === 1" class="space-y-8">
                  <div class="form-section">
                    <h2 class="text-2xl font-bold text-white mb-6">Paper Information</h2>

                    <div class="form-group">
                      <label for="title" class="form-label">Paper Title <span class="text-red-400">*</span></label>
                      <input
                        id="title"
                        v-model="form.title"
                        type="text"
                        required
                        placeholder="Enter the title of your paper"
                        maxlength="500"
                        class="w-full rounded-lg border border-slate-600 bg-slate-800 px-4 py-2.5 text-white focus:border-cyan-500 focus:outline-none focus:ring-2 focus:ring-cyan-500/40"
                        :class="[fieldErrors.title && 'border-red-500']"
                      />
                      <div class="form-char-count">{{ form.title.length }}/40</div>
                      <p v-if="fieldErrors.title" class="text-sm text-red-400 mt-1">{{ fieldErrors.title }}</p>
                    </div>

                    <div class="form-group">
                      <label for="abstract" class="form-label">Abstract <span class="text-red-400">*</span></label>
                      <textarea
                        id="abstract"
                        v-model="form.abstract"
                        required
                        rows="6"
                        placeholder="Provide a comprehensive abstract of your paper (250-300 words)"
                        maxlength="2000"
                        class="w-full rounded-lg border border-slate-600 bg-slate-800 px-4 py-2.5 text-white focus:border-cyan-500 focus:outline-none focus:ring-2 focus:ring-cyan-500/40 resize-none"
                        :class="[fieldErrors.abstract && 'border-red-500']"
                      ></textarea>
                      <div class="form-char-count">{{ form.abstract.length }}/2000</div>
                      <p v-if="fieldErrors.abstract" class="text-sm text-red-400 mt-1">{{ fieldErrors.abstract }}</p>
                    </div>

                    <div class="form-group">
                      <label for="keywords" class="form-label">Keywords <span class="text-red-400">*</span></label>
                      <input
                        id="keywords"
                        v-model="form.keywords"
                        type="text"
                        required
                        placeholder="Enter keywords separated by commas"
                        class="w-full rounded-lg border border-slate-600 bg-slate-800 px-4 py-2.5 text-white focus:border-cyan-500 focus:outline-none focus:ring-2 focus:ring-cyan-500/40"
                        :class="[fieldErrors.keywords && 'border-red-500']"
                      />
                      <p v-if="fieldErrors.keywords" class="text-sm text-red-400 mt-1">{{ fieldErrors.keywords }}</p>
                    </div>

                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                      <div class="form-group">
                        <label for="manuscriptType" class="form-label">Manuscript Type <span class="text-red-400">*</span></label>
                        <select id="manuscriptType" v-model="form.manuscriptType" required class="w-full rounded-lg border border-slate-600 bg-slate-800 px-4 py-2.5 text-white focus:border-cyan-500 focus:outline-none focus:ring-2 focus:ring-cyan-500/40" :class="[fieldErrors.manuscriptType && 'border-red-500']">
                          <option value="">Please select</option>
                          <option value="Article">Original research article</option>
                          <option value="Review">Review article</option>
                          <option value="Letter">Short communication</option>
                          <option value="Case Study">Case study</option>
                          <option value="Editorial">Editorial</option>
                          <option value="Perspective">Perspective article</option>
                          <option value="Comment">Comment</option>
                        </select>
                        <p v-if="fieldErrors.manuscriptType" class="text-sm text-red-400 mt-1">{{ fieldErrors.manuscriptType }}</p>
                      </div>

                      <div class="form-group">
                        <label for="subjectArea" class="form-label ">Subject Area <span class="text-red-400">*</span></label>
                        <div class="relative area-dropdown-container">
                          <div 
                            @click="showAreaDropdown = !showAreaDropdown"
                            class="w-full px-4 py-2.5 bg-slate-800 border border-slate-600 rounded-lg text-white cursor-pointer hover:border-cyan-500 transition-colors min-h-[48px] flex items-center justify-between"
                            :class="[fieldErrors.subjectArea && 'border-red-500']"
                          >
                            <div class="flex flex-wrap gap-2">
                              <span v-if="form.subjectArea.length === 0" class="text-slate-400">
                                Please select
                              </span>
                              <span 
                                v-for="areaName in form.subjectArea" 
                                :key="areaName"
                                class="inline-flex items-center gap-1 px-2 py-1 bg-cyan-600/20 text-cyan-300 rounded text-sm border border-cyan-600/30"
                              >
                                {{ areaName }}
                                <button 
                                  type="button"
                                  @click.stop="removeArea(areaName)"
                                  class="hover:text-cyan-100"
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
                                <div 
                                  v-for="area in getAreasBySubject(subject.subjectId)" 
                                  :key="area.areaId"
                                  @click="selectArea(area)"
                                  class="px-3 py-2 hover:bg-slate-700/50 rounded cursor-pointer transition-colors flex items-center"
                                  :class="{'bg-slate-700/30': isAreaSelected(area.areaName)}"
                                >
                                  <svg v-if="isAreaSelected(area.areaName)" class="w-4 h-4 mr-2 text-cyan-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                                  </svg>
                                  <span class="text-sm text-slate-300">{{ area.areaName }}</span>
                                </div>
                              </div>
                            </div>
                          </div>
                        </div>
                        <p v-if="fieldErrors.subjectArea" class="text-sm text-red-400 mt-1">{{ fieldErrors.subjectArea }}</p>
                      </div>
                    </div>

                    <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
                      <div class="form-group">
                        <label for="wordCount" class="form-label">Word Count <span class="text-red-400">*</span></label>
                        <input 
                          id="wordCount" 
                          v-model.number="form.wordCount" 
                          type="number" 
                          min="0" 
                          required 
                          placeholder="e.g., 5200"
                          class="w-full rounded-lg border border-slate-600 bg-slate-800 px-4 py-2.5 text-white focus:border-cyan-500 focus:outline-none focus:ring-2 focus:ring-cyan-500/40"
                          :class="[fieldErrors.wordCount && 'border-red-500']"
                        />
                        <p v-if="fieldErrors.wordCount" class="text-sm text-red-400 mt-1">{{ fieldErrors.wordCount }}</p>
                      </div>
                      <div class="form-group">
                        <label for="figureCount" class="form-label">Figures</label>
                        <input 
                          id="figureCount" 
                          v-model.number="form.figureCount" 
                          type="number" 
                          min="0" 
                          placeholder="e.g., 5"
                          class="w-full rounded-lg border border-slate-600 bg-slate-800 px-4 py-2.5 text-white focus:border-cyan-500 focus:outline-none focus:ring-2 focus:ring-cyan-500/40"
                        />
                      </div>
                      <div class="form-group">
                        <label for="tableCount" class="form-label">Tables</label>
                        <input 
                          id="tableCount" 
                          v-model.number="form.tableCount" 
                          type="number" 
                          min="0" 
                          placeholder="e.g., 3"
                          class="w-full rounded-lg border border-slate-600 bg-slate-800 px-4 py-2.5 text-white focus:border-cyan-500 focus:outline-none focus:ring-2 focus:ring-cyan-500/40"
                        />
                      </div>
                    </div>

                    <div class="form-group">
                      <label for="coverLetter" class="form-label">Cover Letter</label>
                      <textarea
                        id="coverLetter"
                        v-model="form.coverLetter"
                        rows="4"
                        placeholder="Provide a brief cover letter to the editors (optional)"
                        class="w-full rounded-lg border border-slate-600 bg-slate-800 px-4 py-2.5 text-white focus:border-cyan-500 focus:outline-none focus:ring-2 focus:ring-cyan-500/40 resize-none"
                      ></textarea>
                    </div>
                  </div>

                  <!-- Academic Commitments -->
                  <div class="form-section">
                    <h2 class="text-2xl font-bold text-white mb-6">Academic Commitments</h2>

                    <div class="space-y-4">
                      <label class="checkbox-label">
                        <input 
                          type="checkbox" 
                          v-model="form.declareOriginal" 
                          required 
                          :class="[fieldErrors.declareOriginal && 'input-warning']"
                        />
                        <span class="checkmark"></span>
                        I declare this submission is original and not under consideration elsewhere. *
                      </label>
                      <label class="checkbox-label">
                        <input 
                          type="checkbox" 
                          v-model="form.declareCorresponding" 
                          required 
                          :class="[fieldErrors.declareCorresponding && 'input-warning']"
                        />
                        <span class="checkmark"></span>
                        I am the corresponding author and responsible for communication with the journal. *
                      </label>
                      <label class="checkbox-label">
                        <input 
                          type="checkbox" 
                          v-model="form.declareCorrespondingAuthor" 
                          required 
                          :class="[fieldErrors.declareCorrespondingAuthor && 'input-warning']"
                        />
                        <span class="checkmark"></span>
                        I confirm that I am the corresponding author of this submission. *
                      </label>
                      <label class="checkbox-label">
                        <input 
                          type="checkbox" 
                          v-model="form.declareNoConflict" 
                          required 
                          :class="[fieldErrors.declareNoConflict && 'input-warning']"
                        />
                        <span class="checkmark"></span>
                        All authors declare no competing interests related to this work. *
                      </label>
                      <p v-if="fieldErrors.general" class="form-help field-hint-warning">{{ fieldErrors.general }}</p>
                    </div>
                  </div>
                  <div class="flex-1 max-w-3xl flex flex-col justify-center sm:flex-row gap-3 lg:gap-4 animate-slide-up">

                  <button
                      class="group relative inline-flex items-center gap-2 px-5 py-2.5 lg:px-6 lg:py-3 bg-gradient-to-r from-cyan-500 via-blue-500 to-indigo-500 text-white rounded-full font-semibold text-sm lg:text-base hover:from-cyan-600 hover:via-blue-600 hover:to-indigo-600 hover:shadow-2xl transition-all duration-700 ease-out overflow-hidden"
                      @click="handleNextStep"
                      type="button"
                  >
                    <svg class="w-4 h-4 transition-transform duration-500 ease-out group-hover:rotate-12" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6.253v13m0-13C10.832 5.477 9.246 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.754 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.754 5 16.5 5c1.746 0 3.332.477 4.5 1.253v13C19.832 18.477 18.246 18 16.5 18c-1.746 0-3.332.477-4.5 1.253"></path>
                    </svg>
                    <span v-if="validatingStep1" class="flex items-center gap-2">
                      <svg class="animate-spin h-4 w-4" fill="none" viewBox="0 0 24 24">
                        <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                        <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                      </svg>
                      Validating...
                    </span>
                    <span v-else>Next: Upload Files</span>
                    <div class="absolute inset-0 bg-gradient-to-r from-white to-white opacity-0 group-hover:opacity-20 transition-opacity duration-500"></div>
                    <div class="absolute inset-0 bg-gradient-to-r from-transparent via-white to-transparent opacity-0 transform -skew-x-12 -translate-x-full group-hover:translate-x-full transition-transform duration-1000"></div>
                  </button>
                  </div>
                </div>

                <!-- Step 2: File Upload -->
                <div v-if="currentStep === 2" class="space-y-8">
                  <!-- 提交中的遮罩层 -->
                  <div v-if="submitting" class="fixed inset-0 bg-black/60 backdrop-blur-sm z-50 flex items-center justify-center">
                    <div class="bg-slate-800 rounded-xl p-8 shadow-2xl border border-slate-600 max-w-md w-full mx-4">
                      <div class="flex flex-col items-center space-y-4">
                        <svg class="animate-spin h-12 w-12 text-cyan-500" fill="none" viewBox="0 0 24 24">
                          <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                          <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                        </svg>
                        <h3 class="text-xl font-semibold text-white">正在提交...</h3>
                        <p class="text-sm text-slate-400 text-center">{{ progressMessage }}</p>
                        <div class="w-full bg-slate-700 rounded-full h-2 mt-2">
                          <div 
                            class="bg-gradient-to-r from-cyan-500 to-blue-500 h-2 rounded-full transition-all duration-300" 
                            :style="{ width: submitProgress + '%' }"
                          ></div>
                        </div>
                        <p class="text-xs text-slate-500 mt-1">{{ submitProgress }}%</p>
                      </div>
                    </div>
                  </div>
                  
                  <div class="form-section">
                    <h2 class="text-2xl font-bold text-white mb-6">Upload Files</h2>

                    <!-- 两栏布局 -->
                    <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                      <!-- 左侧：论文文件上传 -->
                      <div class="form-group">
                        <label class="form-label mb-3">Manuscript File <span class="text-red-400">*</span></label>
                        <div class="relative">
                          <input
                            id="paperFile"
                            ref="paperFileInput"
                            type="file"
                            accept=".pdf,.doc,.docx"
                            @change="handlePaperFileUpload"
                            class="hidden"
                            required
                          />
                          <!-- 上传框 -->
                          <div
                            @click="paperFileInput?.click()"
                            class="upload-box border-2 border-dashed border-slate-600 rounded-lg p-8 text-center cursor-pointer hover:border-cyan-500 transition-all duration-300 bg-slate-800/50 hover:bg-slate-800 min-h-[160px] flex flex-col justify-center"
                            :class="[
                              uploadedFiles.paperFile ? 'border-cyan-500' : '',
                              fieldErrors.paperFile && 'border-red-500'
                            ]"
                          >
                            <!-- 未上传状态：显示加号 -->
                            <div v-if="!uploadedFiles.paperFile" class="flex flex-col items-center justify-center">
                              <div class="w-16 h-16 rounded-full bg-slate-700 flex items-center justify-center mb-3">
                                <svg class="w-8 h-8 text-slate-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4"></path>
                                </svg>
                              </div>
                              <p class="text-sm text-slate-300">Click to upload</p>
                              <p class="text-xs text-slate-500 mt-1">PDF, DOC, DOCX (Max 20MB)</p>
                            </div>
                            
                            <!-- 已上传状态：显示文件名和进度 -->
                            <div v-else class="space-y-3">
                              <div class="flex items-center justify-between">
                                <div class="flex-1 min-w-0">
                                  <p class="text-sm text-white font-medium truncate" :title="uploadedFiles.paperFile.originalName">
                                    {{ uploadedFiles.paperFile.originalName }}
                                  </p>
                                  <p class="text-xs text-slate-400 mt-1">
                                    {{ formatFileSize(uploadedFiles.paperFile.fileSize) }}
                                  </p>
                                </div>
                                <button
                                  @click.stop="removePaperFile"
                                  class="ml-2 p-1 text-slate-400 hover:text-red-400 transition-colors"
                                >
                                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                                  </svg>
                                </button>
                              </div>
                              
                              <!-- 进度条（填充符号形式） -->
                              <div class="relative">
                                <div class="w-full bg-slate-700 rounded-full h-2.5 overflow-hidden">
                                  <div
                                    class="bg-gradient-to-r from-cyan-500 to-blue-500 h-2.5 rounded-full transition-all duration-300"
                                    :style="{ width: uploadProgress.paperFile + '%' }"
                                  ></div>
                                </div>
                                <p class="text-xs text-slate-400 mt-1 text-center">
                                  {{ uploadProgress.paperFile < 100 ? `${uploadProgress.paperFile}%` : 'Uploaded' }}
                                </p>
                              </div>
                            </div>
                          </div>
                        </div>
                        <p v-if="fieldErrors.paperFile" class="text-sm text-red-400 mt-2">{{ fieldErrors.paperFile }}</p>
                      </div>

                      <!-- 右侧：支撑材料上传 -->
                      <div class="form-group">
                        <label class="form-label mb-3">Supporting Materials <span class="text-slate-400">(Optional)</span></label>
                        <div class="relative">
                          <input
                            id="supportingFile"
                            ref="supportingFileInput"
                            type="file"
                            accept=".pdf,.doc,.docx,.zip"
                            @change="handleSupportingFileUpload"
                            class="hidden"
                          />
                          <!-- 上传框 -->
                          <div
                            @click="supportingFileInput?.click()"
                            class="upload-box border-2 border-dashed border-slate-600 rounded-lg p-8 text-center cursor-pointer hover:border-cyan-500 transition-all duration-300 bg-slate-800/50 hover:bg-slate-800 min-h-[160px] flex flex-col justify-center"
                            :class="[
                              uploadedFiles.supportingFile ? 'border-cyan-500' : '',
                              fieldErrors.supportingFile && 'border-red-500'
                            ]"
                          >
                            <!-- 未上传状态：显示加号 -->
                            <div v-if="!uploadedFiles.supportingFile" class="flex flex-col items-center justify-center">
                              <div class="w-16 h-16 rounded-full bg-slate-700 flex items-center justify-center mb-3">
                                <svg class="w-8 h-8 text-slate-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4"></path>
                                </svg>
                              </div>
                              <p class="text-sm text-slate-300">Click to upload</p>
                              <p class="text-xs text-slate-500 mt-1">PDF, DOC, DOCX, ZIP (Max 20MB)</p>
                            </div>
                            
                            <!-- 已上传状态：显示文件名和进度 -->
                            <div v-else class="space-y-3">
                              <div class="flex items-center justify-between">
                                <div class="flex-1 min-w-0">
                                  <p class="text-sm text-white font-medium truncate" :title="uploadedFiles.supportingFile.originalName">
                                    {{ uploadedFiles.supportingFile.originalName }}
                                  </p>
                                  <p class="text-xs text-slate-400 mt-1">
                                    {{ formatFileSize(uploadedFiles.supportingFile.fileSize) }}
                                  </p>
                                </div>
                                <button
                                  @click.stop="removeSupportingFile"
                                  class="ml-2 p-1 text-slate-400 hover:text-red-400 transition-colors"
                                >
                                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                                  </svg>
                                </button>
                              </div>
                              
                              <!-- 进度条（填充符号形式） -->
                              <div class="relative">
                                <div class="w-full bg-slate-700 rounded-full h-2.5 overflow-hidden">
                                  <div
                                    class="bg-gradient-to-r from-cyan-500 to-blue-500 h-2.5 rounded-full transition-all duration-300"
                                    :style="{ width: uploadProgress.supportingFile + '%' }"
                                  ></div>
                                </div>
                                <p class="text-xs text-slate-400 mt-1 text-center">
                                  {{ uploadProgress.supportingFile < 100 ? `${uploadProgress.supportingFile}%` : 'Uploaded' }}
                                </p>
                              </div>
                            </div>
                          </div>
                        </div>
                        <p v-if="fieldErrors.supportingFile" class="text-sm text-red-400 mt-2">{{ fieldErrors.supportingFile }}</p>
                      </div>
                    </div>
                  </div>

                  <div class="form-actions">
                    <button 
                      type="button" 
                      @click="currentStep = 1" 
                      class="btn form-btn-secondary"
                    >
                      Back to Information
                    </button>
                    <button 
                      type="button" 
                      @click="handleSubmit" 
                      class="btn btn-primary relative"
                      :disabled="submitting"
                    >
                      <span v-if="submitting" class="flex items-center gap-2">
                        <svg class="animate-spin h-4 w-4" fill="none" viewBox="0 0 24 24">
                          <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                          <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                        </svg>
                        Submitting...
                      </span>
                      <span v-else>Submit Paper</span>
                    </button>
                  </div>
                </div>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </PageScaffold>
</template>

<script setup>
import { ref, reactive, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
import { authApi, apiClient, uploadApi, subjectApi } from '@/api'
import PageScaffold from '@/components/layout/PageScaffold.vue'

const router = useRouter()

const currentStep = ref(1)
const submitting = ref(false)
const validatingStep1 = ref(false)
const progressMessage = ref('正在准备提交...')
const submitProgress = ref(0)

const form = reactive({
  title: '',
  abstract: '',
  keywords: '',
  manuscriptType: '',
  subjectArea: [],
  wordCount: null,
  figureCount: 0,
  tableCount: 0,
  coverLetter: '',
  declareOriginal: false,
  declareCorresponding: false,
  declareCorrespondingAuthor: false,
  declareNoConflict: false
})

const fieldErrors = reactive({})

// Subject area dropdown state
const subjects = ref([])
const areas = ref([])
const showAreaDropdown = ref(false)
const expandedSubjects = ref(new Set())

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

const selectArea = (area) => {
  const areaName = area.areaName
  const index = form.subjectArea.indexOf(areaName)
  if (index > -1) {
    form.subjectArea.splice(index, 1)
  } else {
    form.subjectArea.push(areaName)
  }
}

const removeArea = (areaName) => {
  const index = form.subjectArea.indexOf(areaName)
  if (index > -1) {
    form.subjectArea.splice(index, 1)
  }
}

const isAreaSelected = (areaName) => {
  return form.subjectArea.includes(areaName)
}

const currentUserEmail = ref('')
const currentUserRealName = ref('')

const paperFile = ref(null)
const supportingFile = ref(null)
const paperFileInput = ref(null)
const supportingFileInput = ref(null)
const uploadedFiles = reactive({
  paperFile: null,
  supportingFile: null
})
const uploadProgress = reactive({
  paperFile: 0,
  supportingFile: 0
})

// Create article ID for file upload subdirectory
let articleId = null

// Track if files are selected but not yet uploaded (before articleId is created)
const pendingFileUploads = reactive({
  paperFile: false,
  supportingFile: false
})

// Close dropdown when clicking outside
let clickHandler = null

onMounted(async () => {
  // Already authenticated by router guard
  console.log('Submit page loaded')
  
  // 获取当前登录用户信息
  try {
    const userResponse = await authApi.getCurrentUser()
    if (userResponse && userResponse.data) {
      currentUserEmail.value = userResponse.data.userEmail || ''
      currentUserRealName.value = userResponse.data.userRealName || ''
      console.log('Current user email:', currentUserEmail.value)
      console.log('Current user real name:', currentUserRealName.value)
    }
  } catch (error) {
    console.error('Failed to get current user:', error)
  }
  
  // 尝试从localStorage恢复Basic Information
  const basicInfoStr = localStorage.getItem('article_basic_info')
  if (basicInfoStr) {
    try {
      const basicInfo = JSON.parse(basicInfoStr)
      form.title = basicInfo.title || ''
      form.abstract = basicInfo.abstract || ''
      form.keywords = basicInfo.keywords || ''
      form.manuscriptType = basicInfo.manuscriptType || ''
      form.subjectArea = basicInfo.subjectArea || []
      form.wordCount = basicInfo.wordCount || null
      form.figureCount = basicInfo.figureCount || 0
      form.tableCount = basicInfo.tableCount || 0
      form.coverLetter = basicInfo.coverLetter || ''
      form.declareOriginal = basicInfo.declareOriginal || false
      form.declareCorresponding = basicInfo.declareCorresponding || false
      form.declareCorrespondingAuthor = basicInfo.declareCorrespondingAuthor || false
      form.declareNoConflict = basicInfo.declareNoConflict || false
      console.log('Basic information restored from localStorage')
    } catch (error) {
      console.error('Failed to restore basic information:', error)
    }
  }
  
  // 尝试从localStorage恢复文件上传信息
  const uploadCacheStr = localStorage.getItem('article_file_uploads')
  if (uploadCacheStr) {
    try {
      const uploadCache = JSON.parse(uploadCacheStr)
      if (uploadCache.paperFile) {
        uploadedFiles.paperFile = uploadCache.paperFile
        uploadProgress.paperFile = uploadCache.paperFile.progress || 100
      }
      if (uploadCache.supportingFile) {
        uploadedFiles.supportingFile = uploadCache.supportingFile
        uploadProgress.supportingFile = uploadCache.supportingFile.progress || 100
      }
      console.log('File uploads restored from localStorage')
    } catch (error) {
      console.error('Failed to restore file uploads:', error)
    }
  }
  
  // Load subjects and areas
  try {
    const [subjectResponse, areaResponse] = await Promise.all([
      subjectApi.getSubjectList(),
      subjectApi.getAllAreas()
    ])

    subjects.value = extractList(subjectResponse)
    areas.value = extractList(areaResponse)
  } catch (error) {
    console.error('Failed to load subjects and areas:', error)
    subjects.value = []
    areas.value = []
  }
  
  // Close dropdown when clicking outside
  clickHandler = (e) => {
    if (!e.target.closest('.area-dropdown-container')) {
      showAreaDropdown.value = false
    }
  }
  document.addEventListener('click', clickHandler)
})

onUnmounted(() => {
  if (clickHandler) {
    document.removeEventListener('click', clickHandler)
  }
})

const handlePaperFileUpload = async (event) => {
  const file = event.target.files?.[0]
  if (!file) return

  // Validate file size (20MB limit)
  const maxSize = 20 * 1024 * 1024 // 20MB
  if (file.size > maxSize) {
    fieldErrors.paperFile = 'File size exceeds 20MB limit'
    return
  }

  // Validate file type
  const allowedTypes = ['.pdf', '.doc', '.docx']
  const fileExtension = file.name.toLowerCase().substring(file.name.lastIndexOf('.'))
  if (!allowedTypes.includes(fileExtension)) {
    fieldErrors.paperFile = 'Only PDF, DOC, DOCX files are allowed'
    return
  }

  // Clear any previous errors
  fieldErrors.paperFile = ''
  paperFile.value = file
  uploadProgress.paperFile = 0
  
  // 保存文件信息到localStorage（文件对象无法直接序列化，保存基本信息）
  const fileInfo = {
    originalName: file.name,
    fileSize: file.size,
    fileType: file.type,
    uploadTime: new Date().toISOString()
  }
  uploadedFiles.paperFile = fileInfo
  
  // 保存到localStorage
  const uploadCache = JSON.parse(localStorage.getItem('article_file_uploads') || '{}')
  uploadCache.paperFile = fileInfo
  localStorage.setItem('article_file_uploads', JSON.stringify(uploadCache))
  
  // 立即上传文件（显示进度）
  await uploadFileWithProgress(file, 'paperFile')
}

const handleSupportingFileUpload = async (event) => {
  const file = event.target.files?.[0]
  if (!file) return

  // Validate file size (20MB limit)
  const maxSize = 20 * 1024 * 1024 // 20MB
  if (file.size > maxSize) {
    fieldErrors.supportingFile = 'File size exceeds 20MB limit'
    return
  }

  // Validate file type
  const allowedTypes = ['.pdf', '.doc', '.docx', '.zip']
  const fileExtension = file.name.toLowerCase().substring(file.name.lastIndexOf('.'))
  if (!allowedTypes.includes(fileExtension)) {
    fieldErrors.supportingFile = 'Only PDF, DOC, DOCX, ZIP files are allowed'
    return
  }

  // Clear any previous errors
  fieldErrors.supportingFile = ''
  supportingFile.value = file
  uploadProgress.supportingFile = 0
  
  // 保存文件信息到localStorage
  const fileInfo = {
    originalName: file.name,
    fileSize: file.size,
    fileType: file.type,
    uploadTime: new Date().toISOString()
  }
  uploadedFiles.supportingFile = fileInfo
  
  // 保存到localStorage
  const uploadCache = JSON.parse(localStorage.getItem('article_file_uploads') || '{}')
  uploadCache.supportingFile = fileInfo
  localStorage.setItem('article_file_uploads', JSON.stringify(uploadCache))
  
  // 立即上传文件（显示进度）
  await uploadFileWithProgress(file, 'supportingFile')
}

const handleNextStep = async () => {
  // Validate Step 1
  validatingStep1.value = true
  Object.keys(fieldErrors).forEach(k => delete fieldErrors[k])

  if (!form.title) fieldErrors.title = 'Title is required'
  if (!form.abstract) fieldErrors.abstract = 'Abstract is required'
  if (!form.keywords) fieldErrors.keywords = 'Keywords are required'
  if (!form.manuscriptType) fieldErrors.manuscriptType = 'Manuscript type is required'
  if (!form.subjectArea || form.subjectArea.length === 0) fieldErrors.subjectArea = 'Subject area is required'
  if (form.wordCount == null || form.wordCount < 0) fieldErrors.wordCount = 'Word count is required'
  if (!form.declareOriginal) fieldErrors.declareOriginal = 'Required'
  if (!form.declareCorresponding) fieldErrors.declareCorresponding = 'Required'
  if (!form.declareCorrespondingAuthor) fieldErrors.declareCorrespondingAuthor = 'Required'
  if (!form.declareNoConflict) fieldErrors.declareNoConflict = 'Required'

  validatingStep1.value = false

  if (Object.keys(fieldErrors).length > 0) {
    scrollToFirstError()
    return
  }

  // 保存Basic Information到localStorage
  const basicInfo = {
    title: form.title,
    abstract: form.abstract,
    keywords: form.keywords,
    manuscriptType: form.manuscriptType,
    subjectArea: form.subjectArea,
    wordCount: form.wordCount ?? 0,
    figureCount: form.figureCount ?? 0,
    tableCount: form.tableCount ?? 0,
    coverLetter: form.coverLetter || '',
    declareOriginal: form.declareOriginal,
    declareCorresponding: form.declareCorresponding,
    declareCorrespondingAuthor: form.declareCorrespondingAuthor,
    declareNoConflict: form.declareNoConflict
  }
  localStorage.setItem('article_basic_info', JSON.stringify(basicInfo))
  console.log('Basic information saved to localStorage')
  
  // 页面自动上滑到顶部
  window.scrollTo({ top: 0, behavior: 'smooth' })
  
  currentStep.value = 2
}

// 上传文件并显示进度（仅在前端缓存，不上传到服务器）
const uploadFileWithProgress = async (file, type) => {
  try {
    uploadProgress[type] = 0
    
    // 模拟上传进度（实际文件将在提交时上传）
    const simulateProgress = () => {
      return new Promise((resolve) => {
        let progress = 0
        const interval = setInterval(() => {
          progress += Math.random() * 15
          if (progress >= 100) {
            progress = 100
            clearInterval(interval)
            resolve()
          } else {
            uploadProgress[type] = Math.floor(progress)
          }
        }, 100)
      })
    }
    
    await simulateProgress()
    uploadProgress[type] = 100
    
    // 更新localStorage中的文件信息
    const uploadCache = JSON.parse(localStorage.getItem('article_file_uploads') || '{}')
    if (uploadCache[type]) {
      uploadCache[type].uploaded = true
      uploadCache[type].progress = 100
      localStorage.setItem('article_file_uploads', JSON.stringify(uploadCache))
    }
    
    // 更新uploadedFiles中的进度信息
    if (uploadedFiles[type]) {
      uploadedFiles[type].uploaded = true
    }
  } catch (error) {
    console.error(`Error processing ${type}:`, error)
    fieldErrors[type] = `Failed to process file. Please try again.`
    uploadProgress[type] = 0
  }
}

// 格式化文件大小
const formatFileSize = (bytes) => {
  if (!bytes || bytes === 0) return '0 B'
  const k = 1024
  const sizes = ['B', 'KB', 'MB', 'GB']
  const i = Math.floor(Math.log(bytes) / Math.log(k))
  return Math.round(bytes / Math.pow(k, i) * 100) / 100 + ' ' + sizes[i]
}

// 移除论文文件
const removePaperFile = () => {
  paperFile.value = null
  uploadedFiles.paperFile = null
  uploadProgress.paperFile = 0
  const uploadCache = JSON.parse(localStorage.getItem('article_file_uploads') || '{}')
  delete uploadCache.paperFile
  localStorage.setItem('article_file_uploads', JSON.stringify(uploadCache))
  if (paperFileInput.value) {
    paperFileInput.value.value = ''
  }
}

// 移除支撑材料文件
const removeSupportingFile = () => {
  supportingFile.value = null
  uploadedFiles.supportingFile = null
  uploadProgress.supportingFile = 0
  const uploadCache = JSON.parse(localStorage.getItem('article_file_uploads') || '{}')
  delete uploadCache.supportingFile
  localStorage.setItem('article_file_uploads', JSON.stringify(uploadCache))
  if (supportingFileInput.value) {
    supportingFileInput.value.value = ''
  }
}

const handleSubmit = async () => {
  // Validate Step 2
  Object.keys(fieldErrors).forEach(k => delete fieldErrors[k])

  // 检查必需文件
  if (!paperFile.value || !uploadedFiles.paperFile) {
    fieldErrors.paperFile = 'Paper file is required'
    scrollToFirstError()
    return
  }

  submitting.value = true
  submitProgress.value = 0
  progressMessage.value = '正在准备文件...'

  try {
    // 从localStorage获取Basic Information
    const basicInfoStr = localStorage.getItem('article_basic_info')
    if (!basicInfoStr) {
      throw new Error('Basic information not found in cache')
    }
    const basicInfo = JSON.parse(basicInfoStr)
    
    // 创建FormData对象
    const formData = new FormData()
    
    // 添加Basic Information
    formData.append('title', basicInfo.title)
    formData.append('abstract', basicInfo.abstract)
    formData.append('keywords', basicInfo.keywords)
    formData.append('manuscriptType', basicInfo.manuscriptType)
    formData.append('subjectArea', JSON.stringify(basicInfo.subjectArea))
    formData.append('wordCount', basicInfo.wordCount)
    formData.append('figureCount', basicInfo.figureCount || 0)
    formData.append('tableCount', basicInfo.tableCount || 0)
    formData.append('coverLetter', basicInfo.coverLetter || '')
    
    // 添加当前登录用户邮箱和真实姓名
    if (currentUserEmail.value) {
      formData.append('submitterEmail', currentUserEmail.value)
    }
    if (currentUserRealName.value) {
      formData.append('submitterName', currentUserRealName.value)
    }
    
    // 添加文件
    formData.append('paperFile', paperFile.value)
    if (supportingFile.value) {
      formData.append('supportingFile', supportingFile.value)
    }
    
    // 调用新的提交API
    const response = await apiClient.post('/articles/submit-final', formData, {
      onUploadProgress: (progressEvent) => {
        if (progressEvent.total) {
          // 文件上传阶段占70%的进度
          const uploadPercent = Math.floor((progressEvent.loaded / progressEvent.total) * 70)
          submitProgress.value = uploadPercent
          
          // 根据上传进度更新提示文字
          if (uploadPercent < 20) {
            progressMessage.value = '正在上传文件...'
          } else if (uploadPercent < 40) {
            progressMessage.value = '文件上传中，请稍候...'
          } else if (uploadPercent < 60) {
            progressMessage.value = '文件上传中...'
          } else {
            progressMessage.value = '文件上传即将完成...'
          }
        }
      }
    })

    if (submitProgress.value < 70) {
      submitProgress.value = 70
    }
    progressMessage.value = '文件上传完成，正在保存文件信息...'
    
    // 等待一小段时间，模拟后端处理（实际后端处理是同步的，这里只是为了显示效果）
    await new Promise(resolve => setTimeout(resolve, 20))
    submitProgress.value = 80
    progressMessage.value = '正在创建文章记录...'
    
    await new Promise(resolve => setTimeout(resolve, 20))
    submitProgress.value = 90
    progressMessage.value = '正在联系编辑，发送通知邮件...'
    
    if (response && response.code === 200) {
      // 所有步骤完成
      submitProgress.value = 100
      progressMessage.value = '提交成功！'
      
      // 短暂延迟后跳转，让用户看到成功提示
      await new Promise(resolve => setTimeout(resolve, 800))
      
      // 清除所有提交相关的localStorage缓存
      localStorage.removeItem('article_basic_info')
      localStorage.removeItem('article_file_uploads')
      
      // 显示成功消息并跳转到主页
      alert('提交成功！我们将尽快处理您的文章')
      router.push('/')
    } else {
      submitProgress.value = 0
      progressMessage.value = '提交失败，请重试'
      fieldErrors.general = response?.msg || 'Failed to submit paper. Please try again.'
    }
  } catch (error) {
    console.error('Error submitting paper:', error)
    submitProgress.value = 0
    progressMessage.value = '提交失败，请检查网络连接后重试'
    fieldErrors.general = error.response?.data?.msg || error.message || 'Failed to submit paper. Please try again.'
  } finally {
    submitting.value = false
  }
}

const resetForm = () => {
  form.title = ''
  form.abstract = ''
  form.keywords = ''
  form.manuscriptType = ''
  form.subjectArea = []
  form.wordCount = null
  form.figureCount = 0
  form.tableCount = 0
  form.coverLetter = ''
  form.declareOriginal = false
  form.declareCorresponding = false
  form.declareCorrespondingAuthor = false
  form.declareNoConflict = false
  
  paperFile.value = null
  supportingFile.value = null
  uploadedFiles.paperFile = null
  uploadedFiles.supportingFile = null
  uploadProgress.paperFile = 0
  uploadProgress.supportingFile = 0
  articleId = null
  currentStep.value = 1
  Object.keys(fieldErrors).forEach(k => delete fieldErrors[k])
  
  // 清除localStorage缓存
  localStorage.removeItem('article_basic_info')
  localStorage.removeItem('article_file_uploads')
}

const scrollToFirstError = () => {
  const firstKey = Object.keys(fieldErrors)[0]
  if (!firstKey) return
  const el = document.querySelector(`[data-field="${firstKey}"]`)
  if (el && typeof el.scrollIntoView === 'function') {
    el.scrollIntoView({ behavior: 'smooth', block: 'center' })
  }
}
</script>
<style>
@import "../assets/css/forms.css";
</style>
